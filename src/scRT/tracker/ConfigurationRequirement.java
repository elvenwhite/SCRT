package scRT.tracker;

import java.util.Iterator;

import org.apache.xerces.dom.DeepNodeListImpl;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigurationRequirement {
	private ConfigurationValueSet<ConfigurationValue> CVSet;
	private ConfigurationActionSet<ConfigurationAction> CASet;
	

	
	private String name;
	private String id;
	
	public ConfigurationRequirement(Node item){
		super();
		setCVSet(new ConfigurationValueSet<ConfigurationValue>());
		setCASet(new ConfigurationActionSet<ConfigurationAction>());
		

		
		setId(item.getAttributes().getNamedItem("id").getNodeValue());


		Node tempnode= item.getFirstChild();
		
		while ( tempnode.getNextSibling()!=null){
			

			if (tempnode.getNodeName()=="name") {
				setName(tempnode.getTextContent());
			} else {
				tempnode = tempnode.getNextSibling();
				continue;
			}
			tempnode = tempnode.getNextSibling();

		}
		System.out.println(getId()+"-"+getName());
		extractCV( (NodeImpl) item);
		extractCA( (NodeImpl) item);
	}
	
	public void addCV(ConfigurationValue cv){
		getCVSet().add(cv);
		return;
	}
	public void addCA(ConfigurationAction ca){
		getCASet().add(ca);
		return;
	}

	public ConfigurationValueSet<ConfigurationValue> getCVSet() {
		return CVSet;
	}

	public void setCVSet(ConfigurationValueSet<ConfigurationValue> cVSet) {
		CVSet = cVSet;
	}

	public ConfigurationActionSet<ConfigurationAction> getCASet() {
		return CASet;
	}

	public void setCASet(ConfigurationActionSet<ConfigurationAction> cASet) {
		CASet = cASet;
	}

	public void extractCV(NodeImpl item){
		int length=0;
		ConfigurationValue cv;
		
		DeepNodeListImpl list = new DeepNodeListImpl(item, "ConfigurationValue");
		
//		NodeList list = (DeepNodeListImpl) item.getElementsByTagName("ConfigurationValue");
		length = list.getLength();
		for (int i = 0; i<length;i++){
			cv = new ConfigurationValue(list.item(i));
			CVSet.add(cv);
		}
	}
	
	public void extractCA(NodeImpl item){
		int length=0;
		ConfigurationAction ca;
		
		DeepNodeListImpl list = new DeepNodeListImpl(item, "ConfigurationAction");

//		NodeList list = (DeepNodeListImpl) item.getElementsByTagName("ConfigurationAction");
		length = list.getLength();
		for (int i = 0; i<length;i++){
			ca = new ConfigurationAction(list.item(i));
			CASet.add(ca);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}
	
}
