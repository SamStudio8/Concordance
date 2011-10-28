package concordanceGUI;

import java.awt.BorderLayout;

public class BuildGUI {
	
	private ConcordanceFrame cf;
	
	public BuildGUI(){
		
		//Construct the main frame, add menus and listeners.
		cf = new ConcordanceFrame("Concorderizer", 600, 600, 0, 0);
		cf.setMenu(new ConcordanceMenu());
		cf.add(new ConcordanceOptionsPanel(), BorderLayout.NORTH);
		cf.add(new ConcordanceResultsPanel(), BorderLayout.SOUTH);
		
		//Display the frame.
		cf.displayFrame();
	} 	
}
