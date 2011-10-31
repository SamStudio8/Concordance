package concordance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processes input files and constructs a Concordance object for a given list ofindex words and source text.
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceBuilder {
	
	private Hashtable<String, IndexItem> index;
	private ArrayList<String> orderedIndex;
	private ArrayList<String> contexts;
	
	private String lineBuffer;
	private int sentenceCount;
	private String lineProcess;
	private boolean matchFound;
	
	private static final Pattern terminators = Pattern.compile("([?|!|.])");
	private static final Pattern postTerminators = Pattern.compile("([\"|'|)|}|\\]])");
	private static final Pattern wordside = Pattern.compile("[^A-z]");

	/**
	 * Initialises the data structures and processes the input files.
	 * 
	 * @param indexesFilePath	The new line delimited text file that lists words to use in the concordance index.
	 * @param textFilePath		The text file for which to search for index words and generate a concordance for.
	 * @throws IOException 
	 */
	public ConcordanceBuilder(String indexesFilePath, String textFilePath) throws IOException{
		this.lineBuffer = "";
		this.lineProcess = "";
		this.index = new Hashtable<String, IndexItem>();
		this.orderedIndex = new ArrayList<String>();
		this.contexts = new ArrayList<String>();
				
		this.orderedIndex = this.processIndex(new BufferedReader(new FileReader(indexesFilePath)));
		this.contexts = this.processSource(new BufferedReader(new FileReader(textFilePath)));
	}
	
	/**
	 * Via a BufferedReader, reads the index words file and produces an alphabetically ordered list of index words.<br>
	 * Reading each line, the method converts the index word or index phrase on each line to lowercase and checks if it has already been added.<br>
	 * Once all index words and phrases have been added, the Collections.sort method is called to alphabetize the list.
	 * 
	 * @param file			The BufferedReader for the new line delimited file of index words.
	 * @return				ArrayList<String> - Alphabetized list of index words and phrases
	 * @throws IOException
	 */
	private ArrayList<String> processIndex(BufferedReader file) throws IOException {
		String line = "";
		while((line = file.readLine()) != null){
			line = line.toLowerCase();
			if(!this.orderedIndex.contains(line)){
				this.orderedIndex.add(line);
				this.index.put(line, new IndexItem());
			}
		}
		Collections.sort(this.orderedIndex);
		return this.orderedIndex;
	}
	
	/**
	 * Via a BufferedReader, reads each line of the source text file and passes it to handleLine for processing.<br>
	 * The method also keeps track of the current line number.<br>
	 * Once all lines have been processed, the method returns the list of index word and phrase contexts.
	 * 
	 * @param file			The BufferedReader for the source text file for which to search through for index word occurrences and sentence contexts
	 * @return				ArrayList<String> - The first sentence each index word or phrase appears in.	
	 * @throws IOException
	 */
	private ArrayList<String> processSource(BufferedReader file) throws IOException{
		String line = "";
		int lineCount = 0;
		
		while((line = file.readLine()) != null){
			lineCount++;
			
			//If the line is empty, truncate the lineBuffer.
			//Used for newlines that frequently occur after new chapter headings.
			if(line.length() == 0){
				lineBuffer = "";
				continue;
			}
			
			this.handleLine(lineCount, " "+line);

		}
		return this.contexts;
	}
	
	/**
	 * Handles a line from the user's chosen text file.<br>
	 * The method first checks whether the line has been terminated by a valid 
	 * line terminator such as full stop, exclamation mark or question mark.<br>
	 * If the line is terminated, the line is split into the line-to-process 
	 * (lineProcess) and the remainder (remainder).<br>
	 * The method also checks to see whether terminated lines have a closing 
	 * parenthesis or quotation beyond the terminating character.<br>
	 * Otherwise, the entire line is added to the line buffer (lineBuffer) and 
	 * the entire line is set to the lineProcess variable.<br>
	 * <br>
	 * The lineProcess is then converted to lowercase (as all index words are 
	 * lower cased) and checked for the occurrence of index words and phrases.<br>
	 * If a match is found, the character immediately to the left and right of 
	 * the match are checked for lettered characters to ensure it is a word and 
	 * not a word within a word.<br>
	 * If both the left and right character are both 'clean', the index word or 
	 * phrase's corresponding IndexItem value in the Hashtable is updated with 
	 * the current line number and if necessary, a sentence context reference 
	 * (which points to the element of the list where the sentence will be 
	 * stored when it is terminated).<br>
	 * <br>
	 * Finally, if the line was terminated, it is appended to the line buffer to
	 * finish the sentence, this (providing at least one index word match was 
	 * found) is then added to the list of contexts.<br>
	 * If the remainder variable is not empty, the method is recursively called 
	 * to handle it before moving on to the next line of the source text file. 
	 * 
	 * @param lineCount		The current line number
	 * @param line			The line from the input file to process
	 */
	private void handleLine(int lineCount, String line){
		String remainder = "";
		
		//Check if the line is terminated.
		//lineSentenceTerminated returns -1 for non-terminated lines.
		int terminationIndex = lineSentenceTerminated(line);
		if(terminationIndex != -1){
			
			//Set the line-to-process as the start of the line up to termination.
			//Set the remainder of the line to be everything beyond termination.
			lineProcess = line.substring(0, terminationIndex+1);
			remainder = line.substring(terminationIndex+1);
			
			//If the line is longer than the location of the termination,
			//then we won't get a null pointer checking beyond this point.
			//We can safely check for brackets or quotes after the termination.
			if(terminationIndex != line.length()-1){
				if(checkPostTerminationChar(line.charAt(terminationIndex+1))){
					lineProcess = line.substring(0, terminationIndex+2);
					remainder = line.substring(terminationIndex+2);
				}
			}
			//Append the line to process to the line buffer and continue.
			lineBuffer += lineProcess;
		}
		else{
			//If the line is not terminated, add the whole String to the buffer.
			lineBuffer += line;
			lineProcess = line;
		}
		
		//All index words and phrases are lower case, change the line to match.
		//Iterate over indexes and search the line to process for matches.
		lineProcess = lineProcess.toLowerCase();
		for(String s : orderedIndex){			
			if(lineProcess.contains(s)){
				//If a match is found, we must check it is really a word and not
				//just a word within a word (such as "ay" in "away").
				
				//The character to the left will always be safe to check as a
				//space is appended to the start of every processed line.
				char left = lineProcess.charAt(lineProcess.indexOf(s)-1);
				
				//Assume the character to the right is a fullstop unless
				//otherwise detected below.
				char right = '.';
				
				//Detect if it is safe to check the character to the right.
				if(!(lineProcess.length() == lineProcess.indexOf(s)+s.length())){
					right = lineProcess.charAt(lineProcess.indexOf(s)+s.length());
				}

				//Pass the encapsulating characters to check they are "clean".
				//If they are, add the line number and context if necessary.
				if(this.checkWordsidesClean(left, right)){
					if(this.index.get(s).getContextRef() == -1){
						matchFound = true;
						this.index.get(s).setContextRef(sentenceCount);
					}
					this.index.get(s).addLineNumber(lineCount);
				}
			}
		}
		
		//If at least once match was found, add the line buffer as a context.
		if(terminationIndex != -1){
			if(matchFound){
				this.contexts.add(lineBuffer.trim());
				sentenceCount++;
				matchFound = false;
			}
			
			lineBuffer = "";
			
			//If a remainder exists, recursively call self.
			if(remainder.length() > 1){
				this.handleLine(lineCount, " "+remainder);
			}
		}
	}
	
	/**
	 * Detects whether the currently read line is terminated.<br>
	 * A line is considered terminated (end of sentence) if the line contains 
	 * one of the accepted termination characters such as the full stop.<br>
	 * The line is checked against the this.terminators RegEx pattern.
	 * 
	 * @param line	The line to check for termination.
	 * @return		The location of the termination if it exists, otherwise -1.
	 */
	private int lineSentenceTerminated(String line){
		Matcher matcher = terminators.matcher(line);
		if(matcher.find()){
			return line.indexOf(matcher.group());
		}
		else{
			return -1;
		}
	}
	
	/**
	 * Checks the character following a fullstop is a closing parenthesis or quote.<br>
	 * The character is checked against the accepted post-termination characters
	 * by the this.postTerminators RegEx Pattern.
	 * 
	 * @param ch	The character to check for a post-termination character match.
	 * @return		true if ch matches one of the accepted characters, false otherwise.
	 */
	private boolean checkPostTerminationChar(char ch){
		return postTerminators.matcher(String.valueOf(ch)).find();
	}
	
	/**
	 * Checks whether the encapsulating characters of a matched word are letters or not.<br>
	 * If an index word is found in the line that is currently being processed,
	 * the match checks whether the word is a correct match.<br>
	 * <br> 
	 * For example, the word "ay" would actually match as a word in the string "away".<br>
	 * The method takes parameters of the characters either side of the match, in this
	 * example, 'w' on the left and '.' on the right.<br>
	 * <br>
	 * If either of the two "wordsides" are matched to an alphabetic character, the
	 * method returns false and thus the word is not a correct match.
	 * 
	 * @param leftCh	The character to the left of the match
	 * @param rightCh	The character to the right of the match
	 * @return 			true if both leftCh and rightCh are non-alphabetic characters, false otherwise.
	 */
	private boolean checkWordsidesClean(char leftCh, char rightCh){
		return ((wordside.matcher(String.valueOf(leftCh)).find() == true) 
				&& (wordside.matcher(String.valueOf(rightCh)).find() == true));
	}

	/**
	 * Construct a completed Concordance.<br>
	 * A completed Concordance is only constructed when this method is called
	 * on a ConcordanceBuilder.<br>
	 * This allows for appropriate separation of methods that process input files
	 * and create the Concordance from the methods that return Concordance data.
	 * 
	 * @return		Concordance - A completed Concordance object.
	 */
	public Concordance buildConcordance(){
		return new Concordance(this.index, this.contexts, this.orderedIndex);
	}
}
