package concordanceTest;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import concordance.Concordance;
import concordance.ConcordanceBuilder;

public class ConcordanceBuilderTest {
	
	public Concordance c;

	@Before
	public void setUp() throws Exception {
		ConcordanceBuilder cb = new ConcordanceBuilder("indexes.txt", "source.txt");
		c = cb.buildConcordance();
	}
	
	@Test
	public void getFirstIndex(){
		Assert.assertEquals("explosion", c.getOrderedIndex().get(0));
	}

}
