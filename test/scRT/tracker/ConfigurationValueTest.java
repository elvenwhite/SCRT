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

public class ConfigurationValueTest {
	private Node firstNode, secondNode, thirdNode;

	@Before
	public void prepareDocument() throws Exception {
		Logger.getLogger("scRT").setLevel(Level.OFF);

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\"?>");
		buffer.append("<CRD>");
		buffer.append("<ConfigurationRequirement>");

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
				.getElementsByTagName("ConfigurationValue");

		firstNode = nodeList.item(0);
		secondNode = nodeList.item(1);
		thirdNode = nodeList.item(2);
	}

	@Test
	public void testIntTypeValue() throws Exception {
		ConfigurationValue value = new ConfigurationValue(firstNode);
		assertNotNull("Value should not be null.", value);
		assertEquals("Configuration Value's id is invalid.", "id0001",
				value.getId());
		assertEquals("Configuration Value's name is invalid.", "NAME1",
				value.getName());
		assertEquals("Configuration Value's type is invalid.", "int",
				value.getType());
		assertEquals("Configuration Value's value is invalid.", "10",
				value.getValue());
		assertEquals("Configuration Value's value is invalid.", 10,
				value.getIntValue());
		assertEquals("Configuration Value's value is invalid.", 10F,
				value.getFloatValue(), 0.00001F);
	}

	@Test
	public void testFloatTypeValue() throws Exception {
		ConfigurationValue value = new ConfigurationValue(secondNode);
		assertNotNull("Value should not be null.", value);
		assertEquals("Configuration Value's id is invalid.", "id0002",
				value.getId());
		assertEquals("Configuration Value's name is invalid.", "NAME2",
				value.getName());
		assertEquals("Configuration Value's type is invalid.", "float",
				value.getType());
		assertEquals("Configuration Value's value is invalid.", "-15.4",
				value.getValue());
		assertEquals("Configuration Value's value is invalid.", -15,
				value.getIntValue());
		assertEquals("Configuration Value's value is invalid.", -15.4F,
				value.getFloatValue(), 0.00001F);
	}

	@Test
	public void testUnknownTypeValue() throws Exception {
		ConfigurationValue value = new ConfigurationValue(thirdNode);
		assertNotNull("Value should not be null.", value);
		assertEquals("Configuration Value's id is invalid.", "id0003",
				value.getId());
		assertEquals("Configuration Value's name is invalid.", "Corets, Eva",
				value.getName());
		assertEquals("Configuration Value's type is invalid.", "Fantasy",
				value.getType());
		assertEquals("Configuration Value's value is invalid.",
				"Maeve Ascendant", value.getValue());
		assertEquals("Configuration Value's value is invalid.", 0,
				value.getIntValue());
		assertEquals("Configuration Value's value is invalid.", 0F,
				value.getFloatValue(), 0.00001F);
	}
}
