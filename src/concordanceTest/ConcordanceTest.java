package concordanceTest;

import java.io.IOException;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import concordance.Concordance;
import concordance.ConcordanceBuilder;

/**
 * Concordance.Concordance JUnit Testing Class
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceTest {
	
	private Concordance c;
	private static final String SHAKESPEARE_HATE_CONTEXT = "Grant if thou wilt, thou art beloved of many,   But that thou none lov'st is most evident:   For thou art so possessed with murd'rous hate,   That 'gainst thy self thou stick'st not to conspire,   Seeking that beauteous roof to ruinate   Which to repair should be thy chief desire:   O change thy thought, that I may change my mind,   Shall hate be fairer lodged than gentle love?";
	private static final int SHAKESPEARE_HATE_LINECOUNT = 176;
	
	/**
	 * Constructs an arbitrary Concordance for use in each test.
	 * 
	 * Note that grepping and thorough checking of the source text file used in
	 * this class confirms 176 occurrences of the word "hate" in the shakespeare
	 * text file and the first occurrence of the context as defined in the context constant.
	 * 
	 * Tests in this class have made the Concordance.IndexItem JUnit testing class redundant.
	 * 
	 * @throws IOException
	 */
	@Before
	public void setUp() throws IOException {
		c = new ConcordanceBuilder("shakespeareIndexes.txt", "shakespeare.txt").buildConcordance();
	}
	
	/**
	 * Ensures the correct context for the arbitrary word "hate" was stored
	 * during the generation of the Concordance and subsequently returned.
	 * 
	 * Expected;
	 * *Context to match confirmed context for index word "hate" and shakespeare text.
	 */
	@Test
	public void testContext(){
		Assert.assertEquals(SHAKESPEARE_HATE_CONTEXT, c.getContext("hate"));
	}
	
	/**
	 * Ensures the correct number of lines were detected and stored for a
	 * relatively well used word in a large text source file.
	 * 
	 * Expected;
	 * *Number of elements in lineNumber Vector for index word "hate" to match that of the confirmed number of occurrences in the shakespeare text file.
	 */
	@Test
	public void testLineNumbers(){
		Assert.assertEquals(SHAKESPEARE_HATE_LINECOUNT, c.getLineNumbers("hate").size());
	}
	
	//@Test
	//public void testOccurrencesForIndexWords
}