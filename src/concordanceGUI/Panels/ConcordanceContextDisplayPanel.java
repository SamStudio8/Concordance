package concordanceGUI.Panels;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

/**
 * Panel to display the context of the currently selected index word or phrase.
 * 
 * @author Sam Nicholls (msn)
 */
@SuppressWarnings("serial")
public class ConcordanceContextDisplayPanel extends ConcordancePanel {
	
	private JTextArea context;
	
	/**
	 * Construct the panel with wrapped text area to display contexts.
	 */
	public ConcordanceContextDisplayPanel(){
		setBorder(BorderFactory.createTitledBorder("Context"));
		
		context = new JTextArea();
		context.setLineWrap(true);
		context.setWrapStyleWord(true);
		context.setEditable(false);	
		this.add(context);		
	}
	
	/**
	 * Change the text currently displayed by the panel.
	 * This method will receive a new context to be displayed each time the user
	 * selects a different index word or phrase from the JList on the 
	 * ConcordanceIndexListPanel.
	 * 
	 * @param context The new text the panel should display.
	 */
	public void displayResult(String context) {
		this.context.setText(context);
	}
}
