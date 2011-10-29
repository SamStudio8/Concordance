package concordanceGUI.Panels;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

import concordanceGUI.Listeners.ConcordanceButtonListener;

@SuppressWarnings("serial")
public class ConcordanceOptionsPanel extends ConcordancePanel {
	
	private JTextField index;
	private JTextField text;
	private JButton concordarize;

	public ConcordanceOptionsPanel(){
		setBorder(BorderFactory.createTitledBorder("Build Concordance"));
		this.setLayout(new FlowLayout());
		
		index = new JTextField("Enter Index File Path", 25);
		text = new JTextField("Enter Text File Path", 25);
		//index = new JTextField("/home/Sam/workspace/Concordance/indexes.txt", 15);
		//text = new JTextField("/home/Sam/workspace/Concordance/legacy.txt", 15);
		concordarize = new JButton("Concordarize!");
		
		this.add(index);
		this.add(text);
		this.add(concordarize);
	}

	public String getIndexPath() {
		return this.index.getText();
	}

	public String getTextPath() {
		return this.text.getText();
	}

	public void addButtonListener(ConcordanceButtonListener concordanceButtonListener) {
		this.concordarize.addActionListener(concordanceButtonListener);
	}
}
