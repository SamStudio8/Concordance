package concordanceGUI.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import concordanceGUI.ConcordanceController;
import concordanceGUI.Panels.ConcordanceOptionsPanel;

public class ConcordanceButtonListener implements ActionListener {
	
	private ConcordanceController c;
	
	public ConcordanceButtonListener(ConcordanceController c){
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		if(actionCommand.equals("Concorderize")){
			c.startConcordance();
		}
	}
}
