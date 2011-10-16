package concordance;

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
	 */
	public ConcordanceBuilder(String indexesFilePath, String textFilePath){
		this.index = new Hashtable<String, IndexItem>();
		this.contexts = new ArrayList<String>();
		this.orderedIndex = new ArrayList<String>();
	}
	
	public Concordance buildConcordance(){
		return new Concordance();
	}
	
}
