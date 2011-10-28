package scRT.tracker;

import java.util.HashSet;

import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

public class PropagationSet extends HashSet<Propagation> {

	private static final long serialVersionUID = -1144990263358656759L;

	private static final String TAG_PROPAGATION = "propagation";
	private static final String TAG_TYPE = "type";

	public PropagationSet(Element item) {
		Element element = DOMUtil.getFirstChildElement(item, TAG_PROPAGATION);

		while (element != null) {
			Element typeElement = DOMUtil.getFirstChildElement(element,
					TAG_TYPE);
			String type = DOMUtil.getChildText(typeElement);
			if (type.equals("imperative")) {
				this.add(new ImperativePropagation(element));
			} else if (type.equals("conductive")) {
				// TODO Implement for conductive propagation!!!
			} else if (type.equals("selective")) {
				// TODO Implement for selective propagation!!!
			} else {
			}
			element = DOMUtil.getNextSiblingElement(element, TAG_PROPAGATION);
		}
	}

	public Propagation find(String id) {
		for (Propagation p : this) {
			if (p.id.equals(id)) {
				return p;
			}
		}
		return null;
	}
}
