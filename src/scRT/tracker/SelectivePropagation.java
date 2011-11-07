package scRT.tracker;

import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

import scRT.tracker.exception.PropagationException;

public class SelectivePropagation extends Propagation {

	private ConditionSet co;
	private ConditionSet prco;
	private String sourceId;
	private String targetId;



	public SelectivePropagation(Element item) {
		this.type = SELECTIVE;
		this.co = new ConditionSet();
		this.prco = new PropagationConditionSet();
		
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

		element = DOMUtil.getFirstChildElement(item, "propagationcondition");
		while (element != null){
			String type = DOMUtil.getFirstChildElement(element,	 "type").getTextContent();
			
		}
		
		ConfigurationRequirementSet crs = ConfigurationRequirementSet
				.getInstance();
		crs.findCRbyCRID(this.sourceId).addPR(this);
	}



	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void checkPropagation(ConfigurationValue input)
			throws PropagationException {
		// TODO Auto-generated method stub
		
	}

}
