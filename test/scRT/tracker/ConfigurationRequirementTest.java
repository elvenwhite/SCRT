package scRT.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.apache.xerces.impl.xs.opti.DefaultNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

public class ConfigurationRequirementTest {
	private ConfigurationRequirement cr;
	
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConfigurationRequirement() {
		assert(true);
	}

	@Test
	public void testAddCV() {
		assert(true);
	}

	@Test
	public void testGetCVSet() {
		assert(true);
	}

	@Test
	public void testGetCASet() {
		Node node = new DefaultNode();
		cr = new ConfigurationRequirement(node);
		
		Iterator<ConfigurationValue> iii = cr.getCVSet().iterator();
		assertTrue(iii.hasNext());
		assertEquals("cv1",iii.next().getValue());
	}

}
