package scRT.tracker;

import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

public class Condition {
	private ConfigurationValue cv1;
	private ConfigurationValue cv2;
	private int op;

	public Condition(ConfigurationValue cv1, ConfigurationValue cv2, int op) {
		this.cv1 = cv1;
		this.cv2 = cv2;
		this.op = op;
	}

	public Condition(Element item) {
		Element element = DOMUtil.getFirstChildElement(item, "cv");
		String cv1id = DOMUtil.getAttrValue(element, "id");
		this.cv1 = findConfigurationValue(cv1id);

		element = DOMUtil.getNextSiblingElement(element, "cv");
		String cv2id = DOMUtil.getAttrValue(element, "id");
		this.cv2 = findConfigurationValue(cv2id);

		element = DOMUtil.getFirstChildElement(item, "operator");
		String opText = DOMUtil.getChildText(element).trim();
		if (opText.equals("EQUAL"))
			this.op = Operator.EQUAL;
		else if (opText.equals("LESS_THAN"))
			this.op = Operator.LESS_THAN;
		else if (opText.equals("LESS_THAN_OR_EQUAL"))
			this.op = Operator.LESS_THAN_OR_EQUAL;
		else if (opText.equals("GREATER_THAN"))
			this.op = Operator.GREATER_THAN;
		else if (opText.equals("GREATER_THAN_OR_EQUAL"))
			this.op = Operator.GREATER_THAN_OR_EQUAL;
		else if (opText.equals("NOT_EQUAL"))
			this.op = Operator.NOT_EQUAL;
		else if (opText.equals("IS_SAME_TYPE"))
			this.op = Operator.IS_SAME_TYPE;
		else if (opText.equals("IS_EXIST_IN"))
			this.op = Operator.IS_EXIST_IN;
		else
			throw new RuntimeException("Unknown operator!!!");
	}

	public ConfigurationValue getCv1() {
		return cv1;
	}

	public ConfigurationValue getCv2() {
		return cv2;
	}

	public int getOp() {
		return op;
	}

	public boolean isTrue() {
		switch (this.getOp()) {
		case Operator.EQUAL:
			if (cv1.getValue() == cv2.getValue())
				return true;
			else
				return false;
		case Operator.GREATER_THAN:
			if (Integer.parseInt(cv1.getValue()) > Integer.parseInt(cv2
					.getValue()))
				return true;
			else
				return false;
		case Operator.GREATER_THAN_OR_EQUAL:
			if (Integer.parseInt(cv1.getValue()) >= Integer.parseInt(cv2
					.getValue()))
				return true;
			else
				return false;
		case Operator.IS_EXIST_IN:
			if (cv2.getValue().contains(cv1.getValue()))
				return true;
			else
				return false;
		case Operator.IS_SAME_TYPE:
			if (cv1.getType() == cv2.getType())
				return true;
			else
				return false;
		case Operator.LESS_THAN:
			if (Integer.parseInt(cv1.getValue()) < Integer.parseInt(cv2
					.getValue()))
				return true;
			else
				return false;
		case Operator.LESS_THAN_OR_EQUAL:
			if (Integer.parseInt(cv1.getValue()) <= Integer.parseInt(cv2
					.getValue()))
				return true;
			else
				return false;
		case Operator.NOT_EQUAL:
			if (cv1.getValue() != cv2.getValue())
				return true;
			else
				return false;
		}
		return false;
	}

	private ConfigurationValue findConfigurationValue(String cvId) {
		ConfigurationRequirementSet crs = ConfigurationRequirementSet
				.getInstance();
		for (ConfigurationRequirement cr : crs) {
			for (ConfigurationValue cv : cr.getCVSet()) {
				if (cv.getId().equals(cvId)) {
					return cv;
				}
			}
		}
		return null;
	}
}
