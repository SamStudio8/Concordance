package concordanceGUI;

import java.io.IOException;

import concordance.Concordance;
import concordance.ConcordanceBuilder;

/**
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceModel {
	
	private Concordance c;
	
	/**
	 * Default constructor, initalises the Concordance object.
	 */
	public ConcordanceModel(){
		c = new Concordance();
	}
	
	/**
	 * Attempts to execute the Controller's request to construct a ConcordanceBuilder.
	 * 
	 * @param indexFilePath	The file path of the new line delimited index words and phrases file.
	 * @param textFilePath	The file path of the source text file for which to generate a Concordance.
	 * @return	true if the construction of a ConcordanceBuilder was successful, false if an IOException was encountered.
	 */
	public boolean startConcordanizer(String indexFilePath, String textFilePath){
		try {
			c = new ConcordanceBuilder(indexFilePath, textFilePath).buildConcordance();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * Get the Concordance object the model holds.
	 * 
	 * @return	Concordance - The current Concordance the model contains.
	 */
	public Concordance getConcordance(){
		return c;
	}
}
