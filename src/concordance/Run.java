package concordance;

/**
 * Initializes and runs the Concordance application.
 * Concordarizer is a glorious application that creates a concordance for a given source text.
 * 
 * @author Sam Nicholls (msn)
 */
public class Run {

	/**
	 * Initializes the application and builds the concordance.
	 */
	public static void main(){
		ConcordanceBuilder cb = new ConcordanceBuilder("indexes.txt", "source.txt");
		Concordance c = cb.buildConcordance();
	}
}
