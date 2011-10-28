package scRT.tracker;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

public class ConfigurationRequirement {

	private static Logger log = Logger
			.getLogger(ConfigurationRequirement.class);

	private ConfigurationValueSet CVSet;
	private ConfigurationActionSet CASet;
	private HashSet<Propagation> PRSet;

	private String name;
	private String id;

	public ConfigurationRequirement(Element item) {
		super();

		this.PRSet = new HashSet<Propagation>();
		this.CVSet = new ConfigurationValueSet();
		this.CASet = new ConfigurationActionSet();

		this.id = DOMUtil.getAttrValue(item, "id");

		Element element = DOMUtil.getFirstChildElement(item, "name");
		this.name = DOMUtil.getChildText(element);

		log.debug(getId() + "-" + getName());
		extractCV(item);
		extractCA(item);
	}

	public ConfigurationValueSet getCVSet() {
		return CVSet;
	}

	public ConfigurationActionSet getCASet() {
		return CASet;
	}

	public void extractCV(Element item) {
		Element child = DOMUtil
				.getFirstChildElement(item, "ConfigurationValue");
		while (child != null) {
			ConfigurationValue cv = new ConfigurationValue(child);
			CVSet.add(cv);
			child = DOMUtil.getNextSiblingElement(child, "ConfigurationValue");
		}
	}

	public void extractCA(Element item) {
		Element child = DOMUtil.getFirstChildElement(item,
				"ConfigurationAction");
		while (child != null) {
			ConfigurationAction ca = new ConfigurationAction(child);
			CASet.add(ca);
			child = DOMUtil.getNextSiblingElement(child, "ConfigurationAction");
		}
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Iterable<Propagation> getPropagations() {
		return PRSet;
	}

	public void addPR(Propagation pr) {
		this.PRSet.add(pr);
	}
}
