package scRT.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;


public class ConductivePropagationTest {
	private PropagationSet ps;

	@Before
	public void prepare() throws Exception {
		prepareCR();
		preparePropagation();
	}

	public void prepareCR() throws Exception {
		Logger.getLogger("scRT").setLevel(Level.OFF);

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\"?>					");
		buffer.append("<CRD>									");
		buffer.append("	<ConfigurationRequirement id=\"cr1\">	");
		buffer.append("		<name>EmployeeInfo</name>			");

		buffer.append("		<ConfigurationValue id=\"bk101\">	");
		buffer.append("			<name>NAME1</name>				");
		buffer.append("			<value>10</value>				");
		buffer.append("			<type>int</type>				");
		buffer.append("		</ConfigurationValue>				");

		buffer.append("		<ConfigurationValue id=\"bk104\">	");
		buffer.append("			<name>NAME2</name>				");
		buffer.append("			<value>5</value>				");
		buffer.append("			<type>int</type>				");
		buffer.append("		</ConfigurationValue>				");
		
		buffer.append("	</ConfigurationRequirement>				");
		
		buffer.append("<ConfigurationRequirement id=\"cr2\"> ");
		buffer.append("<name>DisplayEmployeeInfo</name>");
		
		buffer.append("		   <ConfigurationAction id=\"bk112\">");
		buffer.append("		      <name>Galos, Mike</name>");
		buffer.append("		      <arg>Visual Studio 7: A Comprehensive Guide</arg>");
		buffer.append("		      <arg>Computer</arg>");
		buffer.append("		      <arg>49.95</arg>");
		buffer.append("		      <publish_date>2001-04-16</publish_date>");
		buffer.append("		      <description>Microsoft Visual Studio 7 is explored in depth,");
		buffer.append("		      looking at how Visual Basic, Visual C++, C#, and ASP+ are ");
		buffer.append("		      integrated into a comprehensive development ");
		buffer.append("		      environment.</description>");
		buffer.append("		   </ConfigurationAction>");
		   
		buffer.append("	</ConfigurationRequirement>				");
		buffer.append("</CRD>									");

		ByteArrayInputStream bis = new ByteArrayInputStream(buffer.toString()
				.getBytes());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(bis);
		doc.getDocumentElement().normalize();

		ConfigurationRequirementSet.getInstance(doc.getDocumentElement());
	}

	public void preparePropagation() throws Exception {
		Logger.getLogger("scRT").setLevel(Level.OFF);

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\"?>			");
		buffer.append("<PropagationDocuments>			");
		buffer.append("	<propagation id=\"pr2\">		");
		buffer.append("		<type>conductive</type>		");
		buffer.append("		<source id=\"cr1\"/>		");
		buffer.append("		<target id=\"cr2\"/>		");

		buffer.append("			<ca id=\"bk112\"/>		");
	
		buffer.append("</PropagationDocuments>			");

		ByteArrayInputStream bis = new ByteArrayInputStream(buffer.toString()
				.getBytes());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(bis);
		doc.getDocumentElement().normalize();

		ps = new PropagationSet(doc.getDocumentElement());
	}

	@Test
	public void testConductivePropagations() {
		assertEquals(1, ps.size());
		Propagation p = ps.find("pr2");
		assertNotNull(p);
		assertTrue(p instanceof ConductivePropagation);

		ConductivePropagation propagation = (ConductivePropagation) p;
		assertEquals("pr2", propagation.getId());

		ConfigurationAction ca = propagation.getCa();
		assertNotNull(ca);


		assertEquals("bk112", ca.getId());
		assertEquals("Galos, Mike", ca.getName());

		List<String> args = (List<String>) ca.getArgs();
		
		assertTrue(args.contains("49.95"));
	}
}
