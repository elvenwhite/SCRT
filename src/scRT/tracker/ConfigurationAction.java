package scRT.tracker;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class ConfigurationAction {
	private String id;
	private String name;
	private List<String> args;

	public ConfigurationAction(Node item) {
		args = new ArrayList<String>();
		setId(item.getAttributes().getNamedItem("id").getNodeValue());

		Node tempnode= item.getFirstChild();
		
		while (tempnode.getNextSibling()!=null){
			

			if (tempnode.getNodeName()=="name") {
				setName(tempnode.getTextContent());
			} else if (tempnode.getNodeName()=="arg"){
				setArgs(tempnode.getTextContent());
			} else {
				tempnode = tempnode.getNextSibling();
				continue;
			}
			tempnode = tempnode.getNextSibling();
		}	
		System.out.println("CA"+getName()+"-"+getArgs());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}

	public void setArgs(String arg) {
		this.args.add(arg);		
	}


}
