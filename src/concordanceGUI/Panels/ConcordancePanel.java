package concordanceGUI.Panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Abstract superclass for all Concorndance panels.
 * Extends JPanel and allows for changes to be made across all the application's panels.
 * 
 * @author Sam Nicholls (msn)
 */
@SuppressWarnings("serial")
public abstract class ConcordancePanel extends JPanel {
	
	ConcordancePanel(){
		this.setLayout(new BorderLayout());
	}
}
