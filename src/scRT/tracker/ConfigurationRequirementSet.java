package scRT.tracker;

import java.util.HashSet;

import org.w3c.dom.NodeList;

public class ConfigurationRequirementSet extends
		HashSet<ConfigurationRequirement> {

	private static final long serialVersionUID = -7923981409249906869L;

	public ConfigurationRequirementSet(NodeList nl) {
		super();
		int len = nl.getLength();

		for (int i = 0; i < len; i++) {
			ConfigurationRequirement cr = new ConfigurationRequirement(
					nl.item(i));
			this.add(cr);
		}
	}

}
