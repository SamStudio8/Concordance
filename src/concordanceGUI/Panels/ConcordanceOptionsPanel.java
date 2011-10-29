package concordanceGUI.Panels;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

import concordanceGUI.Listeners.ConcordanceButtonListener;

/**
 * Panel to display file path text fields and "Concordarize!" button to user.
 * 
 * @author Sam Nicholls (msn)
 */
@SuppressWarnings("serial")
public class ConcordanceOptionsPanel extends ConcordancePanel {
	
	private JTextField index;
	private JTextField text;
	private JButton concordarize;

	/**
	 * Construct the panel with file path text fields and button.
	 */
	public ConcordanceOptionsPanel(){
		setBorder(BorderFactory.createTitledBorder("Build Concordance"));
		this.setLayout(new FlowLayout());
		
		index = new JTextField("Enter Index File Path", 25);
		text = new JTextField("Enter Text File Path", 25);
		concordarize = new JButton("Concordarize!");
		
		this.add(index);
		this.add(text);
		this.add(concordarize);
	}

	/**
	 * Get the user input path for the index words and phrases file.
	 * @return	String - The user input in the index file path field
	 */
	public String getIndexPath() {
		return this.index.getText();
	}

	/**
	 * Get the user input path for the source text file.
	 * @return String - The user input in the source text file path field
	 */
	public String getTextPath() {
		return this.text.getText();
	}

	/**
	 * Attach a button listener to the buttons on the panel.
	 * @param concordanceButtonListener The button listener to be notifed of state changes to any buttons on this panel.
	 */
	public void addButtonListener(ConcordanceButtonListener concordanceButtonListener) {
		this.concordarize.addActionListener(concordanceButtonListener);
	}
}
