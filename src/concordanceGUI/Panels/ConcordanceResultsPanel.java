package concordanceGUI.Panels;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import concordanceGUI.Listeners.ConcordanceListSelectionListener;

public class ConcordanceResultsPanel extends ConcordancePanel{
	
	DefaultListModel listModel = new DefaultListModel();
	private JList list;
	private JScrollPane listScollPane;
	
	public ConcordanceResultsPanel(){
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		//list.addListSelectionListener(new ConcordanceListSelectionListener(rdp));
		list.setEnabled(false);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(500, 80));
		this.add(listScroller);
	}

	public void addListSelectionListener(ConcordanceListSelectionListener listSelectionListener) {
		list.addListSelectionListener(listSelectionListener);
	}

	public void buildIndexList(ArrayList<String> orderedIndex) {
		listModel.clear();
		for(String s: orderedIndex){
			listModel.addElement(s);
		}
		list.setEnabled(true);
	}

}
