package concordanceGUI.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Defines the rudimentary menu listener for the Concordance application.
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceMenuListener implements ActionListener {
	
	/**
	 * Act on the valid selection of a menu item.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		
		if(actionCommand.equals("Quit")){
			System.exit(0);
		}
	}
}
