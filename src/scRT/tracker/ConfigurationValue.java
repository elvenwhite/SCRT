package scRT.tracker;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigurationValue {
	private static Logger log = Logger.getLogger(ConfigurationValue.class);

	private String id;
	private String name;
	private String type;
	private String value;

	public ConfigurationValue(Node item) {
		this.id = item.getAttributes().getNamedItem("id").getNodeValue();

		NodeList childNodes = item.getChildNodes();
		int childrenLength = childNodes.getLength();
		for (int i = 0; i < childrenLength; i++) {
			Node child = childNodes.item(i);
			String childNodeName = child.getNodeName();
			if (childNodeName.equals("name")) {
				this.name = child.getTextContent();
			} else if (childNodeName.equals("type")) {
				this.type = child.getTextContent();
			} else if (childNodeName.equals("value")) {
				this.value = child.getTextContent();
			} else {
				continue;
			}
		}
		log.debug("CV: name:" + getName() + ", type:" + getType());
	}

	public ConfigurationValue(String id, String value) {
		this.id=id;
		this.value=value;
		// TODO Auto-generated constructor stub
	}

	public String getValue() {
		return value;
	}

	public int getIntValue() {
		return (int) getFloatValue();
	}

	public float getFloatValue() {
		if (!isNumberType()) {
			return 0F;
		}
		try {
			return Float.parseFloat(value);
		} catch (Throwable t) {
			return 0F;
		}
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	private boolean isNumberType() {
		return (type.equals("int") || type.equals("float"));
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o.getClass().equals(getClass())))
			return false;
		ConfigurationValue compare = (ConfigurationValue) o;
		return compare.id.equals(id);
	}
}
