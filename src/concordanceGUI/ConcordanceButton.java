package concordanceGUI;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ConcordanceButton extends JButton {

	ConcordanceButton(String name, ConcordanceButtonListener buttonListener){
		setText(name);
		addActionListener(buttonListener);
	}
}
