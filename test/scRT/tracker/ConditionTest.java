package scRT.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.xerces.util.DOMUtil;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConditionTest {

	private Element conditionElement1, conditionElement2;
	private Element conditionWithInvalidOperatorElement;

	@Before
	public void prepare() throws Exception {
		Logger.getLogger("scRT").setLevel(Level.OFF);
		prepareCR();
		preparePropagation();
	}

	private void prepareCR() throws Exception {
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
		ConfigurationRequirementSet.getInstance(root);
	}

	private void preparePropagation() throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\"?>		");
		buffer.append("<ROOT>						");
		buffer.append("<condition>					");
		buffer.append("	<cv id=\"bk101\"/>			");
		buffer.append("	<operator>					");
		buffer.append("		GREATER_THAN			");
		buffer.append("	</operator>					");
		buffer.append("	<cv id=\"bk104\"/>			");
		buffer.append("</condition>					");
		buffer.append("<condition>					");
		buffer.append("	<cv id=\"bk101\"/>			");
		buffer.append("	<operator>					");
		buffer.append("		EQUAL					");
		buffer.append("	</operator>					");
		buffer.append("	<cv id=\"bk104\"/>			");
		buffer.append("</condition>					");
		buffer.append("<condition>					");
		buffer.append("	<cv id=\"bk101\"/>			");
		buffer.append("	<operator>					");
		buffer.append("		HELLO					");
		buffer.append("	</operator>					");
		buffer.append("	<cv id=\"bk104\"/>			");
		buffer.append("</condition>					");
		buffer.append("</ROOT>						");

		ByteArrayInputStream bis = new ByteArrayInputStream(buffer.toString()
				.getBytes());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(bis);
		doc.getDocumentElement().normalize();

		Element root = doc.getDocumentElement();
		Element element = DOMUtil.getFirstChildElement(root, "condition");
		conditionElement1 = element;
		conditionElement2 = DOMUtil.getNextSiblingElement(conditionElement1,
				"condition");
		conditionWithInvalidOperatorElement = DOMUtil.getNextSiblingElement(
				conditionElement2, "condition");
	}

	@Test
	public void testInvalidOperator() {
		try {
			new Condition(conditionWithInvalidOperatorElement);
			fail("Invalid operator should be checked!!");
		} catch (RuntimeException e) {
		}
	}

	@Test
	public void testOperator() {
		Condition condition = new Condition(conditionElement1);
		assertEquals(Operator.GREATER_THAN, condition.getOp());
	}

	@Test
	public void testOperands() {
		Condition condition = new Condition(conditionElement1);

		ConfigurationValue cv1 = condition.getCv1();
		assertNotNull(cv1);
		assertEquals("bk101", cv1.getId());
		assertEquals(10, cv1.getIntValue());

		ConfigurationValue cv2 = condition.getCv2();
		assertNotNull(cv2);
		assertEquals("bk104", cv2.getId());
		assertEquals(5, cv2.getIntValue());
	}

	@Test
	public void testIsTrue1() {
		Condition condition = new Condition(conditionElement1);
		assertTrue(condition.isTrue());
	}

	@Test
	public void testIsTrue2() {
		Condition condition = new Condition(conditionElement2);
		assertFalse(condition.isTrue());
	}
}
