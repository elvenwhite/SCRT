package scRT.tracker;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

public class ConfigurationAction {
	private static Logger log = Logger.getLogger(ConfigurationAction.class);

	private String id;
	private String name;
	private List<String> args;

	public ConfigurationAction(Element item) {
		this.args = new ArrayList<String>();
		
		this.id = DOMUtil.getAttrValue(item, "id");

		Element element = DOMUtil.getFirstChildElement(item, "name");
		this.name = DOMUtil.getChildText(element);

		element = DOMUtil.getFirstChildElement(item, "arg");
		while (element != null) {
			this.args.add(DOMUtil.getChildText(element));
			element = DOMUtil.getNextSiblingElement(element, "arg");
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

	public void toArray(ConfigurationAction[] caArray) {
		// TODO Auto-generated method stub
		
	}
}
