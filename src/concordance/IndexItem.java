package concordance;

import java.util.LinkedList;

/**
 * Holds concordance data for a particular word in the index.
 * The IndexItem stores the line numbers and a reference to the context of a particular indexed word.
 *  
 * @author Sam Nicholls (msn)
 */
public class IndexItem {
	private LinkedList lineNumbers;
	private int contextRef;
	
	/**
	 * Default IndexItem Constructor.
	 * Initialises lineNumber LinkedList and sets contextRef to -1.
	 */
	public IndexItem(){
		this.lineNumbers = new LinkedList();
		this.contextRef = -1;
	}
	
	public void setContextRef(int ref){
		this.contextRef = ref;
	}
	
	/**
	 * Gets the context reference for this IndexItem.
	 * The context reference points to a String element in the Concordance contexts list.
	 *  
	 * @return The int reference to this IndexItem's context.
	 */
	public int getContextRef(){
		return this.contextRef;
	}
}
