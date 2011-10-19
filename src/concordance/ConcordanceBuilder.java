package concordance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

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
	private int sentenceCount = 0;
	private int terminationIndex;
	private boolean matchFound;
	private int endOfSentence;
	private String remainder;
	private String lineProcess;
	
	private Vector<Character> terminators;
	//private char[] postTerminationChars = { '\'', '"' };

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
		return this.orderedIndex;
	}
	
	public Vector<String> processSource(BufferedReader file) throws IOException{
		String line = "";
		int lineCount = 1;
		
		while((line = file.readLine()) != null){
			this.handleLine(lineCount, line);
			lineCount++;
		}
		return this.contexts;
	}
	
	private void handleLine(int lineCount, String line){
		
		//Reset index word match flag and check if the line passed is terminated.
		terminationIndex = lineSentenceTerminated(line);
		matchFound = false;
		
		//If the line is empty, truncate the lineBuffer.
		//Used for newlines that frequently occur after headings.
		if(line.length() == 0)
			lineBuffer = "";
		
		//If the line is terminated.
		if(terminationIndex != -1){
			lineProcess = line.substring(0, terminationIndex);
			
			//Concat to buffer and prepare to flush unless quote or bracket follows fullstop.
			if(terminationIndex != line.length()-1){
				if(checkPostTerminationChar(line.charAt(terminationIndex+1))){
					endOfSentence = terminationIndex+2;
				}
				else{
					endOfSentence = terminationIndex+1;
				}
				lineBuffer = lineBuffer.concat(line.substring(0, endOfSentence));
			}
			else{
				lineBuffer = lineBuffer.concat(line.substring(0, terminationIndex+1));
			}
		}
		else{
			lineBuffer = lineBuffer.concat(line+" ");
			lineProcess = line;
		}
		
		//Iterate over index words to find matches in this line.
		//If a match is found, check if a context has already been added.
		//If no context, add it to the contexts and save its element reference in the corresponding IndexItem.
		//Otherwise just add its line number.		
		for(String s : orderedIndex){
			if(lineProcess.contains(s)){
				if(this.index.get(s).getContextRef() == -1){
					this.contexts.add(lineBuffer);
					this.index.get(s).setContextRef(sentenceCount);
					break; //TODO Necessary?
				}
				this.index.get(s).addLineNumber(lineCount);
				matchFound = true;
			}
		}
		if(matchFound){	
			sentenceCount++;
			lineBuffer = "";
		}
		
		if(terminationIndex != -1){
			remainder = line.substring(terminationIndex+1);
			this.handleLine(lineCount, remainder);
		}
	}
	
	private int lineSentenceTerminated(String line){
		return line.indexOf(".");
	}
	
	private boolean checkPostTerminationChar(char ch){
		return (ch == '"') || (ch == '\'');
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
