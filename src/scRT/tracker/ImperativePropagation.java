package scRT.tracker;

import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

import scRT.tracker.exception.ImperativeException;
import scRT.tracker.exception.PropagationException;

public class ImperativePropagation extends Propagation {
	private ConditionSet co;
	private String sourceId;
	@SuppressWarnings("unused")
	private String targetId;

	public ImperativePropagation(Element item) {
		this.type = IMPERATIVE;
		this.co = new ConditionSet();

		this.id = DOMUtil.getAttrValue(item, "id");

		Element element = DOMUtil.getFirstChildElement(item, "source");
		this.sourceId = DOMUtil.getAttrValue(element, "id");

		element = DOMUtil.getFirstChildElement(item, "target");
		this.targetId = DOMUtil.getAttrValue(element, "id");

		element = DOMUtil.getFirstChildElement(item, "condition");
		while (element != null) {
			this.co.add(new Condition(element));
			element = DOMUtil.getNextSiblingElement(element, "condition");
		}

		ConfigurationRequirementSet crs = ConfigurationRequirementSet
				.getInstance();
		crs.findCRbyCRID(this.sourceId).addPR(this);
	}

	public String getId() {
		return id;
	}

	public ConditionSet getCo() {
		return co;
	}

	@Override
	public void fire() throws PropagationException {
		// try {
		// checkPropagation();
		// } catch(PropagationException e){
		// throw e;
		// }
		//
	}

	@Override
	public void checkPropagation(ConfigurationValue input)
			throws PropagationException {
		if (!co.validate()) {
			throw new ImperativeException(co.getViolation());
		} else
			return;
	}
}
