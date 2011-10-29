package concordanceGUI.Panels;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import concordanceGUI.Listeners.ConcordanceListSelectionListener;

@SuppressWarnings("serial")
public class ConcordanceIndexListPanel extends ConcordancePanel{
	
	DefaultListModel listModel = new DefaultListModel();
	private JList list;
	
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

	public void addListSelectionListener(ConcordanceListSelectionListener listSelectionListener) {
		list.addListSelectionListener(listSelectionListener);
	}

	public void buildIndexList(Vector<String> orderedIndex, Vector<Integer> occurrenceSum){
		listModel.clear();
		for(int i = 0; i < orderedIndex.size(); i++){
			listModel.addElement(orderedIndex.get(i)+" ("+occurrenceSum.get(i)+")");
		}
		list.setEnabled(true);
	}

}
