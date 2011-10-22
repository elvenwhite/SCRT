package scRT.tracker;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

public class ConfigurationAction {
	private static Logger log = Logger.getLogger(ConfigurationAction.class);

	private String id;
	private String name;
	private List<String> args;

	public ConfigurationAction(Node item) {
		this.args = new ArrayList<String>();
		this.id = item.getAttributes().getNamedItem("id").getNodeValue();

		Node tempnode = item.getFirstChild();

		while (tempnode.getNextSibling() != null) {
			if (tempnode.getNodeName().equals("name")) {
				this.name = tempnode.getTextContent();
			} else if (tempnode.getNodeName().equals("arg")) {
				this.args.add(tempnode.getTextContent());
			} else {
				tempnode = tempnode.getNextSibling();
				continue;
			}
			tempnode = tempnode.getNextSibling();
		}
		log.debug("CA" + getName() + "-" + getArgs());
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getArgCount() {
		return args.size();
	}

	public Iterable<String> getArgs() {
		return args;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o.getClass().equals(getClass())))
			return false;

		ConfigurationAction compare = (ConfigurationAction) o;
		return compare.id.equals(id);
	}
}
