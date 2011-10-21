package scRT.tracker;

public class Condition {
	private ConfigurationValue cv1;
	private ConfigurationValue cv2;
	private int op;
	
	public Condition (ConfigurationValue cv1, ConfigurationValue cv2, int op) {
		setCv1(cv1);
		setCv2(cv2);
		this.setOp(op);
	}

	public ConfigurationValue getCv1() {
		return cv1;
	}

	public void setCv1(ConfigurationValue cv1) {
		this.cv1 = cv1;
	}

	public ConfigurationValue getCv2() {
		return cv2;
	}

	public void setCv2(ConfigurationValue cv2) {
		this.cv2 = cv2;
	}

	public boolean isTrue(){
		switch(this.getOp()) {
			case Operator.EQUAL:
				if (cv1.getValue()==cv2.getValue()) return true ; else return false;
			case Operator.GREATER_THAN:
				if (Integer.parseInt(cv1.getValue())>Integer.parseInt(cv2.getValue()))  return true ; else return false;
			case Operator.GREATER_THAN_OR_EQUAL:
				if (Integer.parseInt(cv1.getValue())>=Integer.parseInt(cv2.getValue())) return true ; else return false;
			case Operator.IS_EXIST_IN:
				if (cv2.getValue().contains(cv1.getValue())) return true ; else return false;
			case Operator.IS_SAME_TYPE:
				if (cv1.getType()==cv2.getType()) return true ; else return false;
			case Operator.LESS_THAN:
				if (Integer.parseInt(cv1.getValue())<Integer.parseInt(cv2.getValue()))  return true ; else return false;
			case Operator.LESS_THAN_OR_EQUAL:
				if (Integer.parseInt(cv1.getValue())<=Integer.parseInt(cv2.getValue()))  return true ; else return false;
			case Operator.NOT_EQUAL:
				if (cv1.getValue()!=cv2.getValue()) return true ; else return false;
		}	
		return false;
	}

	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}
}
