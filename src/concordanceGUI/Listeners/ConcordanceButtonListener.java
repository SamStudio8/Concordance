package concordanceGUI.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import concordanceGUI.ConcordanceController;

/**
 * Defines the button listener and the valid operations that can be performed
 * on a button press.
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceButtonListener implements ActionListener {
	
	private ConcordanceController c;
	
	/**
	 * Default constructor.
	 * @param c	The Concordance application Controller
	 */
	public ConcordanceButtonListener(ConcordanceController c){
		this.c = c;
	}
	
	/**
	 * Act on a button press.
	 * Checks for what button was pressed and informs the application controller.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		if(actionCommand.equals("Concordarize!")){
			c.startConcordance();
		}
	}
}
