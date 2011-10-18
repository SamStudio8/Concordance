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
	private ArrayList<String> contexts;
	private ArrayList<String> orderedIndex;
	private InputReader fileReader;

	/**
	 * Initialises the data structures and processes the input files.
	 * 
	 * @param indexesFilePath	The text file that lists words to use in the concordance index
	 * @param textFilePath		The text file to build a concordance for
	 * @throws IOException 
	 */
	public ConcordanceBuilder(String indexesFilePath, String textFilePath) throws IOException{
		this.index = new Hashtable<String, IndexItem>();
		//this.contexts = new ArrayList<String>();
		//this.orderedIndex = new ArrayList<String>();
		this.fileReader = new InputReader();
		this.orderedIndex = this.fileReader.processIndex(new BufferedReader(new FileReader(indexesFilePath)));
		this.contexts = this.fileReader.processSource(new BufferedReader(new FileReader(textFilePath)));
		//inputReader(new BufferedReader(new FileReader(indexesFilePath)));
		//this.inputReader(new BufferedReader(new FileReader(textFilePath)));
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
