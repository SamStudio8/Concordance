package concordanceGUI.Panels;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import concordanceGUI.Listeners.ConcordanceButtonListener;

public class ConcordanceOptionsPanel extends ConcordancePanel {
	
	private JTextField index;
	private JTextField text;
	private JButton concorderize;

	public ConcordanceOptionsPanel(){
		this.setLayout(new FlowLayout());
		
		//index = new JTextField("Index File Path", 15);
		//text = new JTextField("Text File Path", 15);
		index = new JTextField("/home/Sam/workspace/Concordance/indexes.txt", 15);
		text = new JTextField("/home/Sam/workspace/Concordance/legacy.txt", 15);
		concorderize = new JButton("Concorderize");
		
		this.add(index);
		this.add(text);
		this.add(concorderize);
	}

	public String getIndexPath() {
		return this.index.getText();
	}

	public String getTextPath() {
		return this.text.getText();
	}

	public void addButtonListener(ConcordanceButtonListener concordanceButtonListener) {
		this.concorderize.addActionListener(concordanceButtonListener);
	}
}
