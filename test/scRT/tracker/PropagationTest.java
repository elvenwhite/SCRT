package scRT.tracker;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class PropagationTest {

	private ConfigurationRequirementSet crs;
	private PropagationSet ps;
	@Before
	public void prepare() throws Exception {
		prepareCR();
		preparePropagation();
	}
	public void prepareCR() throws Exception {
		Logger.getLogger("scRT").setLevel(Level.OFF);

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\"?>");
		buffer.append("<CRD>");
		buffer.append("<ConfigurationRequirement  id=\"cr1\">");
		buffer.append("<name>EmployeeInfo</name>");

		buffer.append("<ConfigurationValue id=\"bk101\">");
		buffer.append("<name>NAME1</name>");
		buffer.append("<value>10</value>");
		buffer.append("<type>int</type>");
		buffer.append("</ConfigurationValue>");

		buffer.append("<ConfigurationValue id=\"bk104\">");
		buffer.append("<name>NAME2</name>");
		buffer.append("<value>5</value>");
		buffer.append("<type>int</type>");
		buffer.append("</ConfigurationValue>");

		buffer.append("</ConfigurationRequirement>");
		buffer.append("</CRD>");

		ByteArrayInputStream bis = new ByteArrayInputStream(buffer.toString()
				.getBytes());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(bis);
		doc.getDocumentElement().normalize();

		Element root = doc.getDocumentElement();

		NodeList nodeList = root
				.getElementsByTagName("ConfigurationRequirement");
		crs = ConfigurationRequirementSet.getInstance(nodeList);
	}
	public void preparePropagation() throws Exception {
		Logger.getLogger("scRT").setLevel(Level.OFF);

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\"?>");
		buffer.append("<PropagationDocuments>");
		buffer.append("	<propagation id=\"pr1\">");
		buffer.append("		<type>imperative</type>");

		buffer.append("			<source id=\"cr1\"/>");
		buffer.append("			<target id=\"cr2\"/>");
		buffer.append("			<condition>");
		buffer.append("				<cv id=\"bk101\"/>");
		buffer.append("				<operator>");

		buffer.append("GREATER_THAN");
		buffer.append("</operator>");
		buffer.append("<cv id=\"bk104\"/>");
		buffer.append("</condition>");
		buffer.append("	</propagation>");

		buffer.append("</PropagationDocuments>");

		ByteArrayInputStream bis = new ByteArrayInputStream(buffer.toString()
				.getBytes());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(bis);
		doc.getDocumentElement().normalize();

		Element root = doc.getDocumentElement();

		
		ps = new PropagationSet(root);
	}
	
	@Test
	
	public void trackTest(){
		ConfigurationValue input = new ConfigurationValue("bk101","15");
		Tracker tracker = new Tracker(crs,ps);
		assertTrue(tracker.trackdown(input));
	}
	
	
	
}
