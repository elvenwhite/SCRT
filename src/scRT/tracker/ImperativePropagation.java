package scRT.tracker;

import scRT.tracker.exception.ImperativeException;
import scRT.tracker.exception.PropagationException;

public class ImperativePropagation extends Propagation {
	private ConfigurationRequirement source;
	private ConfigurationRequirement target;
	private ConditionSet co;
	
	public ImperativePropagation(ConfigurationRequirement source,ConfigurationRequirement target, ConditionSet co){
		setSource(source);
		setTarget(target);
		setCo(co);
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
	public void checkPropagation() throws ImperativeException {
		if (!co.validate()){
			ImperativeException e;
			throw e = new ImperativeException(co.getViolation());
		} else return;
	}

	@Override
	public void fire() throws PropagationException {
		try {
			checkPropagation();
		} catch(PropagationException e){
			throw e;
		} 		
	
	}
}
