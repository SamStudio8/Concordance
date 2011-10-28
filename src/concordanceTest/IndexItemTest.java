package concordanceTest;

import java.util.Vector;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import concordance.IndexItem;

public class IndexItemTest{
	
	public IndexItem i;

	@Before
	public void setUp() throws Exception {
		i = new IndexItem();
	}
	
	@Test
	public void testLineNumbers(){
		Vector<Integer> ln = new Vector<Integer>();
		ln.add(5);		
		ln.add(25);
		ln.add(42);
		i.addLineNumber(5);
		i.addLineNumber(25);
		i.addLineNumber(42);
		Assert.assertEquals(3, i.getLineNumbers().size());
		Assert.assertEquals(ln, i.getLineNumbers());
	}
	
	@Test
	public void testContextRef(){
		i.setContextRef(0);
		Assert.assertEquals(0, i.getContextRef());
		i.setContextRef(42);
		Assert.assertEquals(42, i.getContextRef());
	}
}