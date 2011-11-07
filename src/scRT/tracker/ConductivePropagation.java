package scRT.tracker;

import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

import scRT.tracker.exception.PropagationException;

public class ConductivePropagation extends Propagation {





	private String ca;
	private String sourceId;
	private String targetId;



	public ConductivePropagation(Element item) {
		this.type = CONDUCTIVE;

		this.id = DOMUtil.getAttrValue(item, "id");

		Element element = DOMUtil.getFirstChildElement(item, "source");
		this.sourceId = DOMUtil.getAttrValue(element, "id");

		element = DOMUtil.getFirstChildElement(item, "target");
		this.targetId = DOMUtil.getAttrValue(element, "id");

		element = DOMUtil.getFirstChildElement(item, "ca");
		this.ca= DOMUtil.getAttrValue(element, "id");
		
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
	public String getId() {
		return id;
	}
	public ConfigurationAction getCa(){
		ConfigurationRequirementSet crs = ConfigurationRequirementSet
		.getInstance();
		
		return crs.findCAbyCAID(this.ca);
	}
}
