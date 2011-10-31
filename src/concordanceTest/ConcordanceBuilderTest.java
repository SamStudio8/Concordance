package concordanceTest;

import java.io.IOException;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import concordance.Concordance;
import concordance.ConcordanceBuilder;

/**
 * Concordance.ConcordanceBuilder JUnit Testing Class
 * 
 * @author Sam Nicholls (msn)
 */
public class ConcordanceBuilderTest {
	
	private ConcordanceBuilder cb;

	/**
	 * Creates an arbitrary ConcordanceBuilder for use in each test.
	 * 
	 * @throws IOException
	 */
	@Before
	public void setUp() throws IOException {
		cb = new ConcordanceBuilder("indexes.txt", "source.txt");
	}
		
	/**
	 * Tests an arbitrary handful of index words to ensure no word can be
	 * added to the list of index words and phrases more than once, that case
	 * conversion is executed and the list is correctly sorted.
	 * 
	 * The index file used defines;
	 * Xylophone, explosion, EXPLOSION, love, hate, Love, Hate, biscuit, Internet.
	 * 
	 * Expected:
	 * *Ordered words Vector has six elements.
	 * *Xylophone to be final element in array and lowercase.
	 * 
	 * @throws IOException 
	 */
	@Test
	public void processIndex() throws IOException{
		Concordance c = new ConcordanceBuilder("smallIndex.txt", "source.txt").buildConcordance();
		Assert.assertEquals(6, c.getOrderedIndex().size());
		Assert.assertEquals("xylophone", c.getOrderedIndex().get(5));
	}
	
	/**
	 * Tests an arbitrary selection of characters to ensure that a word is only
	 * reported as "clean" if both the left and right characters either side of
	 * it are not alphabetical characters.
	 * 
	 * Expected;
	 * *Tests containing no alphabetical characters in BOTH left and right to assertTrue.
	 * *Tests containing alphabetical characters in EITHER left and right to assertFalse.
	 */
/*	@Test
	public void checkWordSideClean(){
		Assert.assertTrue(cb.checkWordsidesClean('.', '.'));
		Assert.assertTrue(cb.checkWordsidesClean(' ', '.'));
		Assert.assertTrue(cb.checkWordsidesClean(' ', '!'));
		Assert.assertTrue(cb.checkWordsidesClean('.', '0'));
		
		Assert.assertFalse(cb.checkWordsidesClean('a', ' '));
		Assert.assertFalse(cb.checkWordsidesClean(' ', 'a'));
	}
*/	
	/**
	 * Tests an arbitrary selection of characters to ensure that a character
	 * following a termination character (such as a full stop) is only defined
	 * to be a valid "post termination" character such as a closing parenthesis
	 * or closing quotation mark when it matches one of the characters within
	 * the postTerminators RegEx Pattern.
	 * 
	 * Expected;
	 * *Closing parenthesis and closing quotations to assertTrue.
	 * *Other characters such as alphanumeric to assertFalse.
	 * *Opening parenthesis should also assertFalse.
	 */
/*	@Test
	public void checkPostTerminationChar(){
		Assert.assertTrue(cb.checkPostTerminationChar('"'));
		Assert.assertTrue(cb.checkPostTerminationChar('\''));
		Assert.assertTrue(cb.checkPostTerminationChar(')'));
		Assert.assertTrue(cb.checkPostTerminationChar('}'));
		
		Assert.assertFalse(cb.checkPostTerminationChar('.'));
		Assert.assertFalse(cb.checkPostTerminationChar('a'));
		Assert.assertFalse(cb.checkPostTerminationChar('0'));
		Assert.assertFalse(cb.checkPostTerminationChar('('));
		Assert.assertFalse(cb.checkPostTerminationChar('{'));
	}
*/
	/**
	 * Tests an arbitrary selection of lines to ensure that lines are only
	 * defined as terminated when they contain a valid termination character
	 * contained by the terminators RegEx Pattern.
	 * 
	 * Expected;
	 * *Lines containing the full stop, question mark or exclamation mark to return the index of the terminating character in the line.
	 * *Lines not containing one of these permitting characters to return -1.
	 */
/*	@Test
	public void lineSentenceTerminated(){
		Assert.assertEquals(0, cb.lineSentenceTerminated("."));
		Assert.assertEquals(5, cb.lineSentenceTerminated("Hello."));
		Assert.assertEquals(-1, cb.lineSentenceTerminated("How are you"));
	}
*/
}
