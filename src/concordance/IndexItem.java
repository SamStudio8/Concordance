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
}
