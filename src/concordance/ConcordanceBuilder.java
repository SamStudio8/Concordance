package concordance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Builds the Concordance for a given list of index words and text.
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceBuilder {
	
	private Hashtable<String, IndexItem> index;
	private ArrayList<String> orderedIndex;
	private ArrayList<String> contexts;
	
	private String lineBuffer;
	private int sentenceCount = 0;
	private int terminationIndex;
	private boolean matchFound;
	private int endOfSentence;
	
	private char[] postTerminationChars = { '\'', '"' };

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
		this.contexts = new ArrayList<String>();
				
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
	
	public ArrayList<String> processSource(BufferedReader file) throws IOException{
		String line = "";
		int lineCount = 1;
		
		while((line = file.readLine()) != null){
			this.handleLine(lineCount, line);
			lineCount++;
		}
		return this.contexts;
	}
	
	private void handleLine(int lineCount, String line){
		//If line terminated.
		terminationIndex = lineSentenceTerminated(line);
		matchFound = false;
		
		if(terminationIndex != -1){
			
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
			
			//TODO Index word finding!
			//TODO Add line numbers.
			for(String s : orderedIndex){
				if(line.contains(s)){
					if(this.index.get(s).getContextRef() == -1){
						this.index.get(s).setContextRef(sentenceCount);
					}
					this.index.get(s).addLineNumber(lineCount);
					matchFound = true;
				}
			}
			if(matchFound){
				this.contexts.add(this.lineBuffer);
				sentenceCount++;
			}
			
			lineBuffer = "";
			
			//TODO Breaks on ellipsis.
			//TODO Space correction.
			String remainder = line.substring(terminationIndex+1);
			this.handleLine(lineCount, remainder);
		}
		else{
			lineBuffer = lineBuffer.concat(line+" ");
		}
	}
	
	private int lineSentenceTerminated(String line){
		return line.indexOf(".");
	}
	
	private boolean checkPostTerminationChar(char ch){
		//return this.postTerminationChars.
		return (ch == '"') || (ch == '\'');
	}

	public Hashtable<String, IndexItem> getIndex(){
		return this.index;
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
