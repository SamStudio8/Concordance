package concordanceGUI;

/**
 * Initializes and runs the Concordance application.
 * Concordarizer is a glorious application that creates a concordance for a given source text.
 * 
 * @author Sam Nicholls (msn)
 */
public class Run {

	/**
	 * Calls the Concordance application user interface to be constructed.
	 * 
	 * @param args	Command line arguments specified by the user
	 */
	public static void main(String args[]){
		new BuildGUI();
	}
}
