package concordanceGUI.Panels;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConcordanceContextDisplayPanel extends ConcordancePanel {
	
	private JTextArea context;
	
	public ConcordanceContextDisplayPanel(){
		setBorder(BorderFactory.createTitledBorder("Context"));
		
		context = new JTextArea();
		context.setLineWrap(true);
		context.setWrapStyleWord(true);
		context.setEditable(false);	
		this.add(context);		
	}
	
	public void displayResult(String context) {
		this.context.setText(context);
	}
}
