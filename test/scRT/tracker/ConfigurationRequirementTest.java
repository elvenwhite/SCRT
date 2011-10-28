package scRT.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.xerces.util.DOMUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigurationRequirementTest {
	private ConfigurationRequirement requirement1;

	@Before
	public void prepareDocument() throws Exception {
		Logger.getLogger("scRT").setLevel(Level.OFF);

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\"?>");
		buffer.append("<CRD>");
		buffer.append("<ConfigurationRequirement  id=\"cr1\">");
		buffer.append("<name>EmployeeInfo</name>");

		buffer.append("<ConfigurationValue id=\"id0001\">");
		buffer.append("<name>NAME1</name>");
		buffer.append("<value>10</value>");
		buffer.append("<type>int</type>");
		buffer.append("</ConfigurationValue>");

		buffer.append("<ConfigurationValue id=\"id0002\">");
		buffer.append("<name>NAME2</name>");
		buffer.append("<value>-15.4</value>");
		buffer.append("<type>float</type>");
		buffer.append("</ConfigurationValue>");

		buffer.append("<ConfigurationValue id=\"id0003\">");
		buffer.append("<name>Corets, Eva</name>");
		buffer.append("<value>Maeve Ascendant</value>");
		buffer.append("<type>Fantasy</type>");
		buffer.append("</ConfigurationValue>");

		buffer.append("<ConfigurationAction id=\"action0001\">");
		buffer.append("<name>Galos, Mike</name>");
		buffer.append("<arg>Visual Studio 7: A Comprehensive Guide</arg>");
		buffer.append("<arg>Computer</arg>");
		buffer.append("<arg>49.95</arg>");
		buffer.append("<publish_date>2001-04-16</publish_date>");
		buffer.append("<description>Microsoft Visual Studio 7 is explored in depth,");
		buffer.append("looking at how Visual Basic, Visual C++, C#, and ASP+ are");
		buffer.append("integrated into a comprehensive development");
		buffer.append("environment.</description>");
		buffer.append("</ConfigurationAction>");

		buffer.append("</ConfigurationRequirement>");
		buffer.append("</CRD>");

		ByteArrayInputStream bis = new ByteArrayInputStream(buffer.toString()
				.getBytes());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(bis);
		doc.getDocumentElement().normalize();

		Element root = doc.getDocumentElement();
		Element crElement = DOMUtil.getFirstChildElement(root,
				"ConfigurationRequirement");
		requirement1 = new ConfigurationRequirement(crElement);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConfigurationRequirement() {
		assert (true);
	}

	@Test
	public void testAddCV() {
		assert (true);
	}

	@Test
	public void testGetCVSet() {
		assert (true);
	}

	@Test
	public void testGetCASet() {
		assertNotNull("Value should not be null.", requirement1);
		assertEquals("Requirement's id is invalid.", "cr1",
				requirement1.getId());
		assertEquals("Requirement's name is invalid.", "EmployeeInfo",
				requirement1.getName());

		ConfigurationValueSet valueSet = requirement1.getCVSet();
		assertNotNull(valueSet);
		assertEquals(3, valueSet.size());
		ConfigurationValue[] values = new ConfigurationValue[valueSet.size()];
		valueSet.toArray(values);
		assertEquals("id0001", values[0].getId());
		assertEquals("NAME1", values[0].getName());

		ConfigurationActionSet actionSet = requirement1.getCASet();
		assertNotNull(actionSet);
		assertEquals(1, actionSet.size());
		ConfigurationAction[] actions = new ConfigurationAction[actionSet
				.size()];
		actionSet.toArray(actions);
		assertEquals("action0001", actions[0].getId());
		assertEquals("Galos, Mike", actions[0].getName());
	}

}
