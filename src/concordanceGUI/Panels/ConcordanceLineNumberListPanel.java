package concordanceGUI.Panels;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class ConcordanceLineNumberListPanel extends ConcordancePanel {
	
	DefaultListModel listModel = new DefaultListModel();
	private JList list;

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

	public void displayResult(Vector<Integer> lineNumbers) {
		listModel.clear();
		for(Integer i: lineNumbers){
			listModel.addElement(i);
		}
	}

}
