package scRT.tracker;

import org.apache.log4j.Logger;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

public class ConfigurationValue {
	private static Logger log = Logger.getLogger(ConfigurationValue.class);

	private String id;
	private String name;
	private String type;
	private String value;

	public ConfigurationValue(Element item) {
		this.id = DOMUtil.getAttrValue(item, "id");

		Element element = DOMUtil.getFirstChildElement(item, "name");
		this.name = DOMUtil.getChildText(element);

		element = DOMUtil.getFirstChildElement(item, "type");
		this.type = DOMUtil.getChildText(element);

		element = DOMUtil.getFirstChildElement(item, "value");
		this.value = DOMUtil.getChildText(element);

		log.debug("CV: name:" + getName() + ", type:" + getType());
	}

	public ConfigurationValue(String id, String value) {
		this.id = id;
		this.value = value;
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
