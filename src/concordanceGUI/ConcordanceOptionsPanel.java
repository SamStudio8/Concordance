package concordanceGUI;

import java.awt.FlowLayout;

public class ConcordanceOptionsPanel extends ConcordancePanel {

	ConcordanceOptionsPanel(){
		this.setLayout(new FlowLayout());
		ConcordanceButtonListener buttonListener = new ConcordanceButtonListener();
		this.add(new ConcordanceButton("Concorderize", buttonListener));
	}
}
