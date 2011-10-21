package scRT.tracker;

import org.w3c.dom.Node;

public class ConfigurationValue {
	private String id;
	private String name;
	private String type;
	private String value;

	public ConfigurationValue(Node item) {
		setId(item.getAttributes().getNamedItem("id").getNodeValue());


		int i = 0;
		Node tempnode= item.getFirstChild();
		
		while (i<3 && tempnode.getNextSibling()!=null){
			

			if (tempnode.getNodeName()=="name") {
				setName(tempnode.getTextContent());
			} else if (tempnode.getNodeName()=="type"){
				setType(tempnode.getTextContent());
			} else if (tempnode.getNodeName()=="value"){
				setValue(tempnode.getTextContent());
			} else {
				tempnode = tempnode.getNextSibling();
				continue;
			}
			tempnode = tempnode.getNextSibling();
			i++;
		}
		System.out.println("CV"+getName()+"-"+getType());

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
