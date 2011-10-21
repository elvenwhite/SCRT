package scRT.tracker;

import java.util.HashSet;
import java.util.Iterator;

public class ConditionSet extends HashSet<Condition> {
	private ConfigurationRequirement source;
	private ConfigurationRequirement target;
	private String violationmsg;
	public ConditionSet(ConfigurationRequirement source, ConfigurationRequirement target){
		setSource(source);
		setTarget(target);
	}
	
	public void addCondition(ConfigurationValue cv1, ConfigurationValue cv2, int op){
		this.add(new Condition(cv1,cv2,op));
	}

	public boolean validate() {
		Iterator<Condition> iii= this.iterator();
		while (iii.hasNext()){
			Condition con=iii.next();
			if (con.isTrue()) continue; else {
				switch(con.getOp()) {
				case Operator.EQUAL:
					violationmsg = "violation : source CV "+con.getCv1().getName()+"(value:"+con.getCv1().getValue()+") is not equal to targetCV "+con.getCv2().getName()+"(value:"+con.getCv2().getValue()+").";
				case Operator.GREATER_THAN:
					violationmsg = "violation : source CV "+con.getCv1().getName()+"(value:"+con.getCv1().getValue()+") is not equal to targetCV "+con.getCv2().getName()+"(value:"+con.getCv2().getValue()+").";
				case Operator.GREATER_THAN_OR_EQUAL:
					violationmsg = "violation : source CV "+con.getCv1().getName()+"(value:"+con.getCv1().getValue()+") is not equal to targetCV "+con.getCv2().getName()+"(value:"+con.getCv2().getValue()+").";
				case Operator.IS_EXIST_IN:
					violationmsg = "violation : source CV "+con.getCv1().getName()+"(value:"+con.getCv1().getValue()+") is not equal to targetCV "+con.getCv2().getName()+"(value:"+con.getCv2().getValue()+").";
				case Operator.IS_SAME_TYPE:
					violationmsg = "violation : source CV "+con.getCv1().getName()+"(value:"+con.getCv1().getValue()+") is not equal to targetCV "+con.getCv2().getName()+"(value:"+con.getCv2().getValue()+").";
				case Operator.LESS_THAN:
					violationmsg = "violation : source CV "+con.getCv1().getName()+"(value:"+con.getCv1().getValue()+") is not equal to targetCV "+con.getCv2().getName()+"(value:"+con.getCv2().getValue()+").";
				case Operator.LESS_THAN_OR_EQUAL:
					violationmsg = "violation : source CV "+con.getCv1().getName()+"(value:"+con.getCv1().getValue()+") is not equal to targetCV "+con.getCv2().getName()+"(value:"+con.getCv2().getValue()+").";
				case Operator.NOT_EQUAL:
					violationmsg = "violation : source CV "+con.getCv1().getName()+"(value:"+con.getCv1().getValue()+") is not equal to targetCV "+con.getCv2().getName()+"(value:"+con.getCv2().getValue()+").";
			}	
				return false;
			}
		}
		return true;
	}

	public String getViolation() {
		// TODO Auto-generated method stub
		return null;
	}

	public ConfigurationRequirement getSource() {
		return source;
	}

	public void setSource(ConfigurationRequirement source) {
		this.source = source;
	}

	public ConfigurationRequirement getTarget() {
		return target;
	}

	public void setTarget(ConfigurationRequirement target) {
		this.target = target;
	}

}
