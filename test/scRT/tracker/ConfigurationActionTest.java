package scRT.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ConfigurationActionTest {
	private Node firstNode;

	@Before
	public void prepareDocument() throws Exception {
		Logger.getLogger("scRT").setLevel(Level.OFF);

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\"?>");
		buffer.append("<CRD>");
		buffer.append("<ConfigurationRequirement>");

		buffer.append("<ConfigurationAction id=\"id0001\">");
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
		dBuilder.setErrorHandler(new ErrorHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e;
			}

			@Override
			public void fatalError(SAXParseException e) throws SAXException {
				throw e;
			}

			@Override
			public void warning(SAXParseException e) throws SAXException {
				throw e;
			}
		});
		Document doc = dBuilder.parse(bis);
		doc.getDocumentElement().normalize();

		Element root = doc.getDocumentElement();
		NodeList rootList = root
				.getElementsByTagName("ConfigurationRequirement");
		NodeList nodeList = ((Element) rootList.item(0))
				.getElementsByTagName("ConfigurationAction");

		firstNode = nodeList.item(0);
	}

	@Test
	public void testIntTypeValue() throws Exception {
		ConfigurationAction action = new ConfigurationAction(firstNode);
		assertNotNull("Value should not be null.", action);
		assertEquals("Configuration Action's id is invalid.", "id0001",
				action.getId());
		assertEquals("Configuration Action's name is invalid.", "Galos, Mike",
				action.getName());
		assertEquals("Configuration Action's argument count is invalid.", 3,
				action.getArgCount());
		StringBuffer buffer = new StringBuffer();
		for (String arg : action.getArgs()) {
			buffer.append(arg);
			buffer.append("::");
		}
		assertEquals("Configuration Action's arguments are invalid.",
				"Visual Studio 7: A Comprehensive Guide::Computer::49.95::",
				buffer.toString());
	}
}
