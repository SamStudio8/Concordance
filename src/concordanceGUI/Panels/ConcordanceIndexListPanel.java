package concordanceGUI.Panels;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import concordanceGUI.Listeners.ConcordanceListSelectionListener;

/**
 * Panel to display an alphabetized list of index words and phrases for the user
 * to select and view Concordance information for.
 * 
 * @author Sam Nicholls (msn)
 */
@SuppressWarnings("serial")
public class ConcordanceIndexListPanel extends ConcordancePanel{
	
	DefaultListModel listModel = new DefaultListModel();
	private JList list;
	
	/**
	 * Construct the panel with the require JList configuration.
	 */
	public ConcordanceIndexListPanel(){
		setBorder(BorderFactory.createTitledBorder("Indexes"));
		
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
	 * Add a listener to the panel's JList to monitor changes in the selected list element.
	 * 
	 * @param listSelectionListener The listener to use to monitor the JList.
	 */
	public void addListSelectionListener(ConcordanceListSelectionListener listSelectionListener) {
		list.addListSelectionListener(listSelectionListener);
	}

	/**
	 * Populate the panel's JList with the available index words and phrases.
	 * Called when the application controller has built a Concordance and passed
	 * it to the application model.
	 * 
	 * The method resets the JList and then updates it with the new list of Concordance indexes.
	 * For each word or phrase, the number of occurrences found is also shown in brackets.
	 *  
	 * @param orderedIndex		Alphabetized list of all index words and phrases
	 * @param occurrenceSum		For each element of the orderedIndex, the corresponding indexed element in this list contains the number of occurrences found. 
	 */
	public void buildIndexList(ArrayList<String> orderedIndex, ArrayList<Integer> occurrenceSum){
		listModel.clear();
		for(int i = 0; i < orderedIndex.size(); i++){
			listModel.addElement(orderedIndex.get(i)+" ("+occurrenceSum.get(i)+")");
		}
		list.setEnabled(true);
	}

}
