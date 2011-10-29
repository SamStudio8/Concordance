package concordanceTest;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import concordance.Concordance;
import concordance.ConcordanceBuilder;

public class ConcordanceTest {
	
	public ConcordanceBuilder cb;
	public Concordance c;

	@Before
	public void setUp() throws Exception {
		cb = new ConcordanceBuilder("indexes.txt", "legacy.txt");
		c = cb.buildConcordance();
	}
	
	@Test
	public void testHate(){
		Assert.assertEquals("Grant if thou wilt, thou art beloved of many,   But that thou none lov'st is most evident:   For thou art so possessed with murd'rous hate,   That 'gainst thy self thou stick'st not to conspire,   Seeking that beauteous roof to ruinate   Which to repair should be thy chief desire:   O change thy thought, that I may change my mind,   Shall hate be fairer lodged than gentle love?", c.getContext("hate"));
		Assert.assertEquals(176, c.getLineNumbers("hate").size()); //176 lines was confirmed via grep
	}
}