package concordanceGUI.Panels;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Abstract superclass for all Concorndance panels.
 * Extends JPanel and allows for changes to be made across all the application's panels.
 * 
 * @author Sam Nicholls (msn)
 */
@SuppressWarnings("serial")
public abstract class ConcordancePanel extends JPanel {
	
	/**
	 * Default ConcordancePanel constructor.
	 * Sets the panel to use the BorderLayout, other global settings could be
	 * set here that would affect all panels used by the application.
	 */
	ConcordancePanel(){
		this.setLayout(new BorderLayout());
	}
}
