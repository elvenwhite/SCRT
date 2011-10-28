package scRT.tracker;

import java.util.HashSet;
import java.util.Iterator;

public class ConditionSet extends HashSet<Condition> {

	private static final long serialVersionUID = 3737747501061488659L;

	private String violationmsg;

	public boolean validate() {
		Iterator<Condition> iii = this.iterator();
		while (iii.hasNext()) {
			Condition con = iii.next();
			if (con.isTrue())
				continue;
			else {
				switch (con.getOp()) {
				case Operator.EQUAL:
					violationmsg = "violation : source CV "
							+ con.getCv1().getName() + "(value:"
							+ con.getCv1().getValue()
							+ ") is not equal to targetCV "
							+ con.getCv2().getName() + "(value:"
							+ con.getCv2().getValue() + ").";
				case Operator.GREATER_THAN:
					violationmsg = "violation : source CV "
							+ con.getCv1().getName() + "(value:"
							+ con.getCv1().getValue()
							+ ") is not equal to targetCV "
							+ con.getCv2().getName() + "(value:"
							+ con.getCv2().getValue() + ").";
				case Operator.GREATER_THAN_OR_EQUAL:
					violationmsg = "violation : source CV "
							+ con.getCv1().getName() + "(value:"
							+ con.getCv1().getValue()
							+ ") is not equal to targetCV "
							+ con.getCv2().getName() + "(value:"
							+ con.getCv2().getValue() + ").";
				case Operator.IS_EXIST_IN:
					violationmsg = "violation : source CV "
							+ con.getCv1().getName() + "(value:"
							+ con.getCv1().getValue()
							+ ") is not equal to targetCV "
							+ con.getCv2().getName() + "(value:"
							+ con.getCv2().getValue() + ").";
				case Operator.IS_SAME_TYPE:
					violationmsg = "violation : source CV "
							+ con.getCv1().getName() + "(value:"
							+ con.getCv1().getValue()
							+ ") is not equal to targetCV "
							+ con.getCv2().getName() + "(value:"
							+ con.getCv2().getValue() + ").";
				case Operator.LESS_THAN:
					violationmsg = "violation : source CV "
							+ con.getCv1().getName() + "(value:"
							+ con.getCv1().getValue()
							+ ") is not equal to targetCV "
							+ con.getCv2().getName() + "(value:"
							+ con.getCv2().getValue() + ").";
				case Operator.LESS_THAN_OR_EQUAL:
					violationmsg = "violation : source CV "
							+ con.getCv1().getName() + "(value:"
							+ con.getCv1().getValue()
							+ ") is not equal to targetCV "
							+ con.getCv2().getName() + "(value:"
							+ con.getCv2().getValue() + ").";
				case Operator.NOT_EQUAL:
					violationmsg = "violation : source CV "
							+ con.getCv1().getName() + "(value:"
							+ con.getCv1().getValue()
							+ ") is not equal to targetCV "
							+ con.getCv2().getName() + "(value:"
							+ con.getCv2().getValue() + ").";
				}
				return false;
			}
		}
		return true;
	}

	public String getViolation() {
		return violationmsg;
	}
}
