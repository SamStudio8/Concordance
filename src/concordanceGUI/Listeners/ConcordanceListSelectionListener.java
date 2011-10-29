package concordanceGUI.Listeners;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import concordanceGUI.ConcordanceController;

/**
 * Defines the list selection listener that monitors action on the list of index
 * words and phrases that are displayed to the user.
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceListSelectionListener implements ListSelectionListener {

	private JList list;
	private ConcordanceController c;
	
	/**
	 * Default constructor.
	 * @param c	The Concordance application Controller
	 */
	public ConcordanceListSelectionListener(ConcordanceController c){
		this.c = c;
	}
	
	/**
	 * Act on a change of list selection.
	 * Sends the element number of the new selection to the application controller.
	 */
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		this.list = (JList)lse.getSource();
		if(lse.getValueIsAdjusting() == true){
			c.loadResult(list.getSelectedIndex());
		}
	}
}
