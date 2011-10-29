package concordance;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Holds the Concordance for a user specified list of index words and a given text.
 * Index words are held in the index Hashtable with keys to IndexItems that store line numbers and a context.
 * 
 * @author Sam Nicholls (msn)
 */
public class Concordance {

	private Hashtable<String, IndexItem> index;
	private Vector<String> contexts;
	private Vector<String> orderedIndex;
	
	/**
	 * Default Concordance constructor, initialises data structures.
	 */
	public Concordance() {
		this.index = new Hashtable<String, IndexItem>();
		this.contexts = new Vector<String>();
		this.orderedIndex = new Vector<String>();
	}
	
	/**
	 * Constructs a completed Concordance.
	 * Once the ConcordanceBuilder has finished processing the input files, it constructs a Concordance object.
	 * 
	 * @param index			The Hashtable containing all index word keys and their IndexItem values 
	 * @param contexts		The Vector containing the contexts for the index words
	 * @param orderedIndex	An alphabetically sorted Vector of index words to be used for display in the GUI
	 */
	public Concordance(Hashtable<String, IndexItem> index, Vector<String> contexts, Vector<String> orderedIndex){
		this.index = index;
		this.contexts = contexts;
		this.orderedIndex = orderedIndex;
	}

	/**
	 * Get the context for a particular index word.
	 * The context is the first sentence this word appears in from the chosen source text.
	 * 
	 * @param forWord	The index word to get the context for
	 * @return			String - The first sentence this index word appears in, or a message stating that no context for the word was found.
	 */
	public String getContext(String forWord){
		IndexItem returnItem = index.get(forWord);
		if(returnItem == null || returnItem.getContextRef() == -1){
			return "No context found for "+forWord+", it probably didn't appear in the source text.";
		}
		else{
			return contexts.get(returnItem.getContextRef());
		}
	}
	
	/**
	 * Return the line numbers for a given index word.
	 * Each element of the Vector contains the line number of an occurrence of the index word in the chosen source text.
	 * 
	 * @param forWord	The index word to get the line number occurrences for
	 * @return			Vector<Integer> - Line numbers of each occurrence of the given index word
	 */
	public Vector<Integer> getLineNumbers(String forWord){	
		IndexItem indexItem = index.get(forWord);
		if(indexItem != null && indexItem.getLineNumbers().size() >= 0){
			return indexItem.getLineNumbers();
		}
		else{
			return new Vector<Integer>();
		}
	}
	
	/**
	 * Return the alphabetically sorted Vector of index words.
	 * Used by the user interface to create a JList of index words for the user to select.
	 * 
	 * @return			Vector<String> - The alphabetically sorted list of index words
	 */
	public Vector<String> getOrderedIndex(){
		return this.orderedIndex;
	}
	
	/**
	 * Returns the number of line occurrences in the source text of each word in the ordered index.
	 * Used by the user interface to add a bracketed number of instances for each index word in the JList.
	 * Each element in the Vector directly corresponds to the same element in the contexts Vector.
	 * 
	 * @return			Vector<Integer> - Occurrences in the source text for each word in the contexts Vector
	 */
	public Vector<Integer> getOccurrencesForIndexWords(){
		Vector<Integer> occurences = new Vector<Integer>();
		for(String s : this.orderedIndex){
			occurences.add(this.getLineNumbers(s).size());
		}
		return occurences;
	}
}
