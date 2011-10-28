package concordanceTest;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import concordance.Concordance;
import concordance.ConcordanceBuilder;

public class ConcordanceBuilderTest {
	
	public ConcordanceBuilder cb;
	public Concordance c;

	@Before
	public void setUp() throws Exception {
		cb = new ConcordanceBuilder("indexes.txt", "source.txt");
	}
		
	@Test
	public void checkWordSideClean(){
		Assert.assertTrue(cb.checkWordsidesClean('.', '.'));
		Assert.assertTrue(cb.checkWordsidesClean(' ', '.'));
		Assert.assertTrue(cb.checkWordsidesClean(' ', '!'));
		Assert.assertTrue(cb.checkWordsidesClean('.', '0'));
		
		Assert.assertFalse(cb.checkWordsidesClean('a', ' '));
		Assert.assertFalse(cb.checkWordsidesClean(' ', 'a'));
	}
	
	@Test
	public void checkPostTerminationChar(){
		Assert.assertTrue(cb.checkPostTerminationChar('"'));
		Assert.assertTrue(cb.checkPostTerminationChar('\''));
		Assert.assertTrue(cb.checkPostTerminationChar(')'));
		Assert.assertTrue(cb.checkPostTerminationChar('}'));
		
		Assert.assertFalse(cb.checkPostTerminationChar('.'));
		Assert.assertFalse(cb.checkPostTerminationChar('a'));
		Assert.assertFalse(cb.checkPostTerminationChar('0'));
	}

	@Test
	public void lineSentenceTerminated(){
		Assert.assertEquals(0, cb.lineSentenceTerminated("."));
		Assert.assertEquals(5, cb.lineSentenceTerminated("Hello."));
		Assert.assertEquals(-1, cb.lineSentenceTerminated("How are you"));
	}
}
