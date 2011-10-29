package concordanceGUI;

/**
 * Builds the graphical user interface for the Concordance application.
 * 
 * @author Sam Nicholls (msn)
 */
public class BuildGUI {
	
	/**
	 * Construct the graphical user interface.
	 * Creates a ConcordanceModel and ConcordanceView that manage the state of
	 * the application. A ConcordanceController that manages interaction between
	 * the Model and View is also constructed.
	 * 
	 * The ConcordanceView is then set to be displayed to the user.
	 */
	public BuildGUI(){
		ConcordanceModel m = new ConcordanceModel();
		ConcordanceView v = new ConcordanceView();
		new ConcordanceController(v, m);
		v.displayView();
	} 	
}
