package concordance;

import java.util.ArrayList;

/**
 * Holds concordance data for a particular word in the index.
 * The IndexItem stores the line numbers and a reference to the context of a particular indexed word.
 *  
 * @author Sam Nicholls (msn)
 */
public class IndexItem {
	private ArrayList<Integer> lineNumbers;
	private int contextRef;
	
	/**
	 * Default IndexItem Constructor.
	 * Initialises lineNumber LinkedList and sets contextRef to -1.
	 */
	public IndexItem(){
		this.lineNumbers = new ArrayList<Integer>();
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

	public void addLineNumber(int lineCount) {
		// TODO Auto-generated method stub
		this.lineNumbers.add(lineCount);
	}
	
	public ArrayList<Integer> getLineNumbers(){
		return this.lineNumbers;
	}
}
