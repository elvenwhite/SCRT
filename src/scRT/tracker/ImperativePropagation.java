package scRT.tracker;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import scRT.tracker.exception.ImperativeException;
import scRT.tracker.exception.PropagationException;

public class ImperativePropagation extends Propagation {
	private ConfigurationRequirement source;
	private ConfigurationRequirement target;
	private ConditionSet co;
	private String id;
	private String sourceid;
	private String targetid;
	
	public ImperativePropagation(ConfigurationRequirement source,ConfigurationRequirement target, ConditionSet co){
		setSource(source);
		setTarget(target);
		setCo(co);
	}

	public ImperativePropagation(Node item) {
		this.id = item.getAttributes().getNamedItem("id").getNodeValue();
		this.co= new ConditionSet();
		NodeList childNodes = item.getChildNodes();
		int childrenLength = childNodes.getLength();
		for (int i = 0; i < childrenLength; i++) {
			Node child = childNodes.item(i);
			String childNodeName = child.getNodeName();
			if (childNodeName.equals("source")) {
				this.sourceid = child.getAttributes().getNamedItem("id").getNodeValue();
			} else if (childNodeName.equals("target")) {
				this.targetid =child.getAttributes().getNamedItem("id").getNodeValue();
			} else if (childNodeName.equals("condition")) {
				this.co.add(new Condition(child));
			} else {
				continue;
			}
		}
		
		ConfigurationRequirementSet crs=ConfigurationRequirementSet.getInstance();
		crs.findCRbyCRID(this.sourceid).addPR(this);
		
	}

	public ConditionSet getCo() {
		return co;
	}

	public void setCo(ConditionSet co2) {
		this.co = co2;
	}

	public ConfigurationRequirement getTarget() {
		return target;
	}

	public void setTarget(ConfigurationRequirement target) {
		this.target = target;
	}

	public ConfigurationRequirement getSource() {
		return source;
	}

	public void setSource(ConfigurationRequirement source) {
		this.source = source;
	}



	@Override
	public void fire() throws PropagationException {
//		try {
//			checkPropagation();
//		} catch(PropagationException e){
//			throw e;
//		} 		
//	
	}

	@Override
	public void checkPropagation(ConfigurationValue input)
			throws PropagationException {
		// TODO Auto-generated method stub
		if (!co.validate()){
			ImperativeException e;
			throw e = new ImperativeException(co.getViolation());
		} else return;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
