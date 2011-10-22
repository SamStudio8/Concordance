package concordance;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Holds the Concordance for a particular list of indexes and a source text.
 * Index words are held in a hashtable with keys to IndexItems that store line numbers and context.
 * 
 * @author Sam Nicholls (msn)
 */
public class Concordance {

	private Hashtable<String, IndexItem> index;
	private Vector<String> contexts;
	private ArrayList<String> orderedIndex;
	
	/**
	 * Constructs a Concordance.
	 * 
	 * @param index			The processed hashtable, containing all index words and keys
	 * @param contexts		A list of contexts for all index words
	 * @param orderedIndex	An ordered list of index words to provide an alphabetical list
	 */
	public Concordance(Hashtable<String, IndexItem> index, Vector<String> contexts, ArrayList<String> orderedIndex){
		this.index = index;
		this.contexts = contexts;
		this.orderedIndex = orderedIndex;
	}
	
	/**
	 * Get the context for a particular index word.
	 * The context is the first sentence this word appears in from the source text.
	 * 
	 * @param forWord	The index word to find a context for
	 * @return			A sentence this index word appears in as a String
	 */
	public String getContext(String forWord){
		IndexItem returnItem = index.get(forWord);
		if(returnItem == null){
			return "No index found for "+forWord+".";
		}
		else{
			return contexts.get(returnItem.getContextRef()).trim();
		}
	}
	
	public Vector<String> getContexts(){
		return this.contexts;
	}
	
	public ArrayList<String> getOrderedIndex(){
		return this.orderedIndex;
	}
	
	public void printContexts(){
		for(int i=0; i < this.contexts.size(); i++){
			System.out.println(i+" "+contexts.get(i));
		}
	}
	
	public Vector<Integer> getLineNumbers(String forWord){
		return this.index.get(forWord).getLineNumbers();
	}
}
