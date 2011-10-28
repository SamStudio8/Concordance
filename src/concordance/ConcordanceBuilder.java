package concordance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Builds the Concordance for a given list of index words and text.
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceBuilder {
	
	private Hashtable<String, IndexItem> index;
	private ArrayList<String> orderedIndex;
	private Vector<String> contexts;
	
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
	 * @param indexesFilePath	The text file that lists words to use in the concordance index
	 * @param textFilePath		The text file to build a concordance for
	 * @throws IOException 
	 */
	public ConcordanceBuilder(String indexesFilePath, String textFilePath) throws IOException{
		this.lineBuffer = "";
		
		this.index = new Hashtable<String, IndexItem>();
		this.orderedIndex = new ArrayList<String>();
		this.contexts = new Vector<String>();
				
		this.orderedIndex = this.processIndex(new BufferedReader(new FileReader(indexesFilePath)));
		this.contexts = this.processSource(new BufferedReader(new FileReader(textFilePath)));
	}
	
	/**
	 * Reads an input file and produces an ordered list of words.
	 * @param file			The text file that contains a word-per-line list of index words
	 * @throws IOException
	 */
	public ArrayList<String> processIndex(BufferedReader file) throws IOException {
		//TODO Order the words.
		String line = "";
		while((line = file.readLine()) != null){
			this.orderedIndex.add(line);
			this.index.put(line, new IndexItem());
		}
		Collections.sort(this.orderedIndex);
		return this.orderedIndex;
	}
	
	/**
	 * 
	 * @param file			The input file for which to create a Concordance for
	 * @return				
	 * @throws IOException
	 */
	public Vector<String> processSource(BufferedReader file) throws IOException{
		String line = "";
		int lineCount = 1;
		
		while((line = file.readLine()) != null){

			//If the line is empty, truncate the lineBuffer.
			//Used for newlines that frequently occur after headings.
			if(line.length() == 0){
				lineBuffer = "";
			}
			
			this.handleLine(lineCount, " "+line);
			lineCount++;
		}
		return this.contexts;
	}
	
	/**
	 * Handles a given line from an input file.
	 * The method checks whether the line is terminated
	 * ...whether the line contains any index words
	 *...recursively calls itself
	 * 
	 * @param lineCount		The current line number
	 * @param line			The line from the input file to process
	 */
	private void handleLine(int lineCount, String line){
		//Reset index word match flag and check if the line passed is terminated.
		int terminationIndex = lineSentenceTerminated(line);
		String remainder = "";
		
		//If the line is terminated...
		if(terminationIndex != -1){
			
			//..set the line to process as the start of the line up to termination.
			//Set the remainder of the line to be everything beyond termination.
			lineProcess = line.substring(0, terminationIndex+1);
			remainder = line.substring(terminationIndex+1);
			
			//If the line is longer than the location of the terminated char, it's safe to check beyond it.
			//This checks if there are quote or bracket chars following the regular line terminators.
			if(terminationIndex != line.length()-1){
				//TODO Passing space via method to allow char to become a charSequence.
				if(checkPostTerminationChar(line.charAt(terminationIndex+1))){
					lineProcess = line.substring(0, terminationIndex+2);
					remainder = line.substring(terminationIndex+2);
				}
			}
			//Append to the line buffer and continue.
			lineBuffer += lineProcess;
		}
		else{
			//If the line is not terminated, add the whole thing to the buffer.
			lineBuffer += line;
			lineProcess = line;
		}
		
		//Iterate over index words, search string for matches.
		//If a match is found, set the context reference for the corresponding IndexItem if one is not set.
		//Add the line number to the IndexItem, then continue the loop to the next word.
		for(String s : orderedIndex){
			//TODO Case sensitivity?
			if(lineProcess.contains(s)){
				
				//Assume the character is a fullstop, unless detected otherwise below.
				char right = '.';

				//The character to the left will always be safe to check as an empty
				//space is appended to the start of each processed line.
				char left = lineProcess.charAt(lineProcess.indexOf(s)-1);
				
				//Detect if it is safe to check the character to the right.
				if(!(lineProcess.length() == lineProcess.indexOf(s)+s.length())){
					right = lineProcess.charAt(lineProcess.indexOf(s)+s.length());
				}

				if(this.checkWordsidesClean(left, right)){
					if(this.index.get(s).getContextRef() == -1){
						matchFound = true;
						this.index.get(s).setContextRef(sentenceCount);
					}
					this.index.get(s).addLineNumber(lineCount);
					//continue;
				}
			}
		}
		
		if(terminationIndex != -1){
			if(matchFound){
				this.contexts.add(lineBuffer.trim());
				sentenceCount++;
				matchFound = false;
			}
			
			lineBuffer = "";
			
			//Save empty recursive calls by checking the remainder contains something.
			//If a remainder exists, call this method again.
			if(remainder.length() > 1){
				this.handleLine(lineCount, remainder);
			}
		}
	}
	
	/**
	 * Detects whether the currently read line is terminated.
	 * A line is considered terminated (end of sentence) if the line contains one of the accepted termination characters.
	 * 
	 * @param line	The current line from the input file to check for termination.
	 * @return		The location of the termination if the line is terminated by an accepted character such as the full stop, otherwise -1.
	 */
	public int lineSentenceTerminated(String line){
		Matcher matcher = terminators.matcher(line);
		if(matcher.find()){
			return line.indexOf(matcher.group());
		}
		else{
			return -1;
		}
	}
	
	/**
	 * Checks whether the character following a fullstop is a closing parenthesis or quote.
	 * 
	 * @param ch	The character to check for a post-termination character match.
	 * @return		true if ch matches one of the accepted post-termination characters.
	 */
	public boolean checkPostTerminationChar(char ch){
		return postTerminators.matcher(String.valueOf(ch)).find();
	}
	
	/**
	 * Checks whether the encapsulating characters of a matched word are letters or not.
	 * If an index word is found in the line that is currently being processed,
	 * the match checks whether the word is a correct match.
	 * 
	 * For example, the word "ay" would actually match as a word in the string "away".
	 * The method takes parameters of the characters either side of the match, in this
	 * example, 'w' on the left and '.' on the right.
	 * 
	 * If either of the two "wordsides" are matched to an alphabetic character, the
	 * method returns false and thus the word is not a correct match.
	 * 
	 * @param leftCh	The character to the left of the word to check
	 * @param rightCh	The character to the right of the word to check
	 * @return 			true if both leftCh and rightCh are non-alphabetic characters, false otherwise.
	 */
	public boolean checkWordsidesClean(char leftCh, char rightCh){
		boolean b = ((wordside.matcher(String.valueOf(leftCh)).find() == true) && (wordside.matcher(String.valueOf(rightCh)).find() == true));
//		boolean left = wordside.matcher(String.valueOf(leftCh)).find();
//		boolean right = wordside.matcher(String.valueOf(rightCh)).find();
//		System.out.println("LEFT: "+leftCh+"("+left+")\tRIGHT: "+rightCh+"("+right+")\t\tEVAL: "+b);
		return b;
	}
	
	/**
	 * Construct a completed Concordance.
	 * 
	 * @return	A completed Concordance object.
	 */
	public Concordance buildConcordance(){
		return new Concordance(this.index, this.contexts, this.orderedIndex);
	}
}
