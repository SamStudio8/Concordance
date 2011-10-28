package concordanceGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConcordanceMenuListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		
		if(actionCommand.equals("Quit")){
			System.exit(0);
		}
	}
}
