package concordance;

import java.io.IOException;
import concordanceGUI.*;

/**
 * Initializes and runs the Concordance application.
 * Concordarizer is a glorious application that creates a concordance for a given source text.
 * 
 * @author Sam Nicholls (msn)
 */
public class Run {

	/**
	 * Initializes the application and builds the concordance.
	 * 
	 * @param args			Any command line arguments specified by the user
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException{
		//TODO Missing input file.
		//ConcordanceBuilder cb = new ConcordanceBuilder("indexes.txt", "legacy.txt");
		//Concordance model = cb.buildConcordance();
		BuildGUI b = new BuildGUI();
		
		//TODO Getcontext for word that doesn't have a context fails.
	}
}
