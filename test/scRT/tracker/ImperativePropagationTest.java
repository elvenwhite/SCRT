package scRT.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class ImperativePropagationTest {

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
		buffer.append("	<propagation id=\"pr1\">		");
		buffer.append("		<type>imperative</type>		");
		buffer.append("		<source id=\"cr1\"/>		");
		buffer.append("		<target id=\"cr2\"/>		");
		buffer.append("		<condition>					");
		buffer.append("			<cv id=\"bk101\"/>		");
		buffer.append("			<operator>				");
		buffer.append("				GREATER_THAN		");
		buffer.append("			</operator>				");
		buffer.append("			<cv id=\"bk104\"/>		");
		buffer.append("		</condition>				");
		buffer.append("	</propagation>					");

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
	public void testImperativePropagations() {
		assertEquals(1, ps.size());
		Propagation p = ps.find("pr1");
		assertNotNull(p);
		assertTrue(p instanceof ImperativePropagation);

		ImperativePropagation propagation = (ImperativePropagation) p;
		assertEquals("pr1", propagation.getId());

		ConditionSet conditions = propagation.getCo();
		assertNotNull(conditions);

		assertEquals(1, conditions.size());

		Condition[] conditionArray = new Condition[1];
		conditions.toArray(conditionArray);

		assertEquals(Operator.GREATER_THAN, conditionArray[0].getOp());
		ConfigurationValue cv1 = conditionArray[0].getCv1();
		assertNotNull(cv1);
		assertEquals("bk101", cv1.getId());
		assertEquals(10, cv1.getIntValue());

		ConfigurationValue cv2 = conditionArray[0].getCv2();
		assertNotNull(cv2);
		assertEquals("bk104", cv2.getId());
		assertEquals(5, cv2.getIntValue());
	}
}
