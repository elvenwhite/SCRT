package scRT.tracker;

import java.util.HashSet;

import org.w3c.dom.NodeList;

public class ConfigurationRequirementSet extends
		HashSet<ConfigurationRequirement> {

	private static ConfigurationRequirementSet instance = null;

	public static ConfigurationRequirementSet getInstance() {
		if (instance == null)
			throw new RuntimeException("Not initialized!!!!");
		return instance;
	}

	public static ConfigurationRequirementSet getInstance(NodeList nl) {
		if (instance == null) {
			instance = new ConfigurationRequirementSet(nl);
		}

		return instance;
	}

	private static final long serialVersionUID = -7923981409249906869L;

	private ConfigurationRequirementSet(NodeList nl) {
		super();
		int len = nl.getLength();

		for (int i = 0; i < len; i++) {
			ConfigurationRequirement cr = new ConfigurationRequirement(
					nl.item(i));
			this.add(cr);
		}
	}

	public ConfigurationRequirement findCRbyCVID(String id) {
		// TODO Auto-generated method stub
		for (ConfigurationRequirement cr : this) {
			for (ConfigurationValue cv : cr.getCVSet()) {
				if (cv.getId().equals(id))
					return cr;
			}
		}
		return null;
	}
	
	public ConfigurationRequirement findCRbyCRID(String id){
		for (ConfigurationRequirement cr : this) {
				if (cr.getId().equals(id))
					return cr;
			
		}
		return null;
	}
}
