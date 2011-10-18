package concordance;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Processes input text files.
 * @author Sam Nicholls (msn)
 */
public class InputReader {

	private ArrayList<String> orderedIndex;
	private ArrayList<String> contexts;
	private String lineBuffer;
	
	public InputReader(){
		this.lineBuffer = "";
		this.orderedIndex = new ArrayList<String>();
		this.contexts = new ArrayList<String>();
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
			System.out.println(line);
			this.orderedIndex.add(line);
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
	
	private void handleLine(int count, String line){
		//If line terminated.
		int terminationIndex = lineSentenceTerminated(line);
		if(terminationIndex != -1){
			//Concat to buffer and prepare to flush.
			lineBuffer.concat(line.substring(0, terminationIndex));
			contexts.add(lineBuffer);
			//String remainder = line.substring(terminationIndex);
		}
		else{
			lineBuffer.concat(line);
		}
	}
	
	private int lineSentenceTerminated(String line){
		return line.indexOf(".");
	}
}
