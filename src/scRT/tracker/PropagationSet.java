package scRT.tracker;

import java.util.HashSet;

import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PropagationSet extends HashSet<Propagation> {

	private static final long serialVersionUID = -1144990263358656759L;

	public PropagationSet(Element node) {
		// TODO Auto-generated constructor stub
		NodeList nodelist = node.getElementsByTagName("propagation");
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node item = nodelist.item(i);

			String id = item.getAttributes().getNamedItem("id").getNodeValue();
			String type = ((Element) item).getElementsByTagName("type").item(0)
					.getTextContent();

			
			if (type.equals("imperative")) {
				ImperativePropagation ip = new ImperativePropagation(item);
				ip.setId(id);
				this.add(ip);
			}
		}

	}

	public PropagationSet() {
		// TODO Auto-generated constructor stub
	}

	public void extract(Document doc) {
	}

}
