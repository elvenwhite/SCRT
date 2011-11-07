package scRT.tracker;

import java.util.HashSet;

import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

public class ConfigurationRequirementSet extends
		HashSet<ConfigurationRequirement> {

	private static ConfigurationRequirementSet instance = null;

	public static ConfigurationRequirementSet getInstance() {
		if (instance == null)
			throw new RuntimeException("Not initialized!!!!");
		return instance;
	}

	public static ConfigurationRequirementSet getInstance(Element item) {
		if (instance == null) {
			instance = new ConfigurationRequirementSet(item);
		}

		return instance;
	}

	private static final long serialVersionUID = -7923981409249906869L;

	private ConfigurationRequirementSet(Element item) {
		super();
		Element element = DOMUtil.getFirstChildElement(item,
				"ConfigurationRequirement");
		while (element != null) {
			ConfigurationRequirement cr = new ConfigurationRequirement(element);
			this.add(cr);
			element = DOMUtil.getNextSiblingElement(element,
					"ConfigurationRequirement");
		}
	}

	public ConfigurationRequirement findCRbyCVID(String id) {
		for (ConfigurationRequirement cr : this) {
			for (ConfigurationValue cv : cr.getCVSet()) {
				if (cv.getId().equals(id))
					return cr;
			}
		}
		return null;
	}

	public ConfigurationRequirement findCRbyCRID(String id) {
		for (ConfigurationRequirement cr : this) {
			if (cr.getId().equals(id))
				return cr;
		}
		return null;
	}

	public ConfigurationAction findCAbyCAID(String id) {
		for (ConfigurationRequirement cr : this) {
			for (ConfigurationAction ca: cr.getCASet()){
				if (ca.getId().equals(id))
					return ca;
			}
		}
		return null;
	}
}
