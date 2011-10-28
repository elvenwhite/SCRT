package scRT.tracker;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.DeepNodeListImpl;
import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.Node;

public class ConfigurationRequirement {

	private static Logger log = Logger
			.getLogger(ConfigurationRequirement.class);

	private ConfigurationValueSet CVSet;
	private ConfigurationActionSet CASet;
	private HashSet<Propagation> PRSet;
	
	private String name;
	private String id;

	public ConfigurationRequirement(Node item) {
		super();
		PRSet = new HashSet<Propagation>();
		setCVSet(new ConfigurationValueSet());
		setCASet(new ConfigurationActionSet());

		this.id = item.getAttributes().getNamedItem("id").getNodeValue();

		Node tempnode = item.getFirstChild();

		while (tempnode.getNextSibling() != null) {

			if (tempnode.getNodeName().equals("name")) {
				this.name = tempnode.getTextContent();
			} else {
				tempnode = tempnode.getNextSibling();
				continue;
			}
			tempnode = tempnode.getNextSibling();

		}
		log.debug(getId() + "-" + getName());
		extractCV((NodeImpl) item);
		extractCA((NodeImpl) item);
	}

	public void addCV(ConfigurationValue cv) {
		getCVSet().add(cv);
		return;
	}

	public void addCA(ConfigurationAction ca) {
		getCASet().add(ca);
		return;
	}

	public ConfigurationValueSet getCVSet() {
		return CVSet;
	}

	public void setCVSet(ConfigurationValueSet cVSet) {
		CVSet = cVSet;
	}

	public ConfigurationActionSet getCASet() {
		return CASet;
	}

	public void setCASet(ConfigurationActionSet cASet) {
		CASet = cASet;
	}

	public void extractCV(NodeImpl item) {
		int length = 0;
		
		ConfigurationValue cv;

		DeepNodeListImpl list = new DeepNodeListImpl(item, "ConfigurationValue");

		length = list.getLength();
		for (int i = 0; i < length; i++) {
			cv = new ConfigurationValue(list.item(i));
			CVSet.add(cv);
		}
	}

	public void extractCA(NodeImpl item) {
		int length = 0;
		ConfigurationAction ca;

		DeepNodeListImpl list = new DeepNodeListImpl(item,
				"ConfigurationAction");

		length = list.getLength();
		for (int i = 0; i < length; i++) {
			ca = new ConfigurationAction(list.item(i));
			CASet.add(ca);
		}
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public HashSet<Propagation> getPropagations() {
		return PRSet;
	}

	public void addPR(Propagation pr) {
		this.PRSet.add(pr);
	}
}
