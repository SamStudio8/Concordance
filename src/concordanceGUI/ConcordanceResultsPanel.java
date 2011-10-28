package concordanceGUI;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ConcordanceResultsPanel extends ConcordancePanel{
	
	private JList list;
	
	ConcordanceResultsPanel(){
		Object[] data = new Object[10];
		data[0] = 5;
		data[1] = 15;
		list = new JList(data); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ConcordanceListSelectionListener());
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(500, 80));
		this.add(listScroller);
	}
}
