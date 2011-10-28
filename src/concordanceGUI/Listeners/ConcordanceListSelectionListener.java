package concordanceGUI.Listeners;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import concordanceGUI.ConcordanceController;

public class ConcordanceListSelectionListener implements ListSelectionListener {

	private JList list;
	private ConcordanceController c;
	
	public ConcordanceListSelectionListener(ConcordanceController c){
		this.c = c;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		this.list = (JList)lse.getSource();
		if(lse.getValueIsAdjusting() == true){
			c.loadResult(list.getSelectedIndex());
		}
	}
}
