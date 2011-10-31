package concordanceGUI.Panels;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Panel to display list of line number occurrences for the currently selected
 * index word or phrase.
 * 
 * @author Sam Nicholls (msn)
 */
@SuppressWarnings("serial")
public class ConcordanceLineNumberListPanel extends ConcordancePanel {
	
	DefaultListModel listModel = new DefaultListModel();
	private JList list;

	/**
	 * Construct the panel with the require JList configuration.
	 */
	public ConcordanceLineNumberListPanel() {
		setBorder(BorderFactory.createTitledBorder("Line Occurrences"));
		
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.setEnabled(false);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(500, 80));
		this.add(listScroller);
	}

	/**
	 * Resets and re-populates the panel's JList with line number occurrences
	 * for the currently selected index word or phrase.
	 * Called when the application controller is informed that the user has
	 * selected a different element in the JList of indexes on the
	 * ConcordanceIndexListPanel. The controller will pass the new line numbers
	 * to be displayed.
	 * 
	 * @param lineNumbers	LinkedList of line number occurrences for a given index word or phrase.
	 */
	public void displayResult(LinkedList<Integer> lineNumbers) {
		listModel.clear();
		for(Integer i: lineNumbers){
			listModel.addElement(i);
		}
	}

}
