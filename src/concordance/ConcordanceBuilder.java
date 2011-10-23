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
		//this.fileReader = new InputReader();
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
	 * Reads an input source file and checks for matches of index words.
	 * @param file
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
				if(checkPostTerminationChar(" "+line.charAt(terminationIndex+1))){
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
			//TODO Detect actual words.
			//TODO " "+s loses words that are preceded by brackets etc. - ^A-Za-z
			//TODO Case sensitivity?
			//if(lineProcess.contains(s)){
			if(lineProcess.contains(s)){
				if(this.index.get(s).getContextRef() == -1){
					matchFound = true;
					this.index.get(s).setContextRef(sentenceCount);
				}
				this.index.get(s).addLineNumber(lineCount);
				continue;
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
	
	private int lineSentenceTerminated(String line){
		Matcher matcher = terminators.matcher(line);
		if(matcher.find()){
			return line.indexOf(matcher.group());
		}
		else{
			return -1;
		}
	}
	
	public boolean checkPostTerminationChar(CharSequence ch){
		return postTerminators.matcher(ch).find();
	}
	
	private boolean checkWordsides(CharSequence ch){
		return wordside.matcher(ch).find();
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
