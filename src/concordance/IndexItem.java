package concordance;

import java.util.LinkedList;

/**
 * Holds concordance data for a particular index word or phrase.<br>
 * The IndexItem stores each occurrence line number of the index word or phrase
 * in the source text file, along with a reference to the element in the
 * Concordance's contexts list for the first sentence it appears in.
 *  
 * @author Sam Nicholls (msn)
 */
public class IndexItem {

	private LinkedList<Integer> lineNumbers;
	private int contextRef;
	
	/**
	 * Default IndexItem Constructor.<br>
	 * Initialises lineNumbers and sets contextRef to -1.
	 */
	public IndexItem(){
		this.lineNumbers = new LinkedList<Integer>();
		this.contextRef = -1;
	}

	/**
	 * Set the sentence context reference for this IndexItem.<br>
	 * The context for a index word or phrase is the first sentence in which it 
	 * appears in the source text file.<br>This field references an element in 
	 * the contexts list of the Concordance in which the sentence has been placed.
	 * 
	 * @param ref  	The element of the Concordance's contexts list that contains the context for this index word or phrase.
	 */
	public void setContextRef(int ref){
		this.contextRef = ref;
	}
	
	/**
	 * Get the context reference for this IndexItem.<br>
	 * The context for a index word or phrase is the first sentence in which it
	 * appears in the source text file.<br>This field references an element in the 
	 * contexts list of the Concordance in which the sentence has been placed.
	 * 
	 * @return  	The element of the Concordance's contexts list that contains the context for this index word or phrase.
	 */
	public int getContextRef(){
		return this.contextRef;
	}

	/**
	 * Add a line number occurrence to this IndexItem.<br>
	 * An element in this IndexItem's lineNumber list is created each time
	 * the word is found in the source text file, containing the line number
	 * the index word or phrase was detected on.
	 * 
	 * @param lineCount	The line number of the source text file an occurrence has been found in.
	 */
	public void addLineNumber(int lineCount) {
		this.lineNumbers.add(lineCount);
	}
	
	/**
	 * Return the list of line number occurrences for this IndexItem.
	 * 
	 * @return	LinkedList<Integer> - The list that stores all the line number occurrences for this IndexItem.
	 */
	public LinkedList<Integer> getLineNumbers(){
		return this.lineNumbers;
	}
}
