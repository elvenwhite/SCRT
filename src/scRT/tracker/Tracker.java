package scRT.tracker;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.xni.XMLLocator;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import scRT.parser.SCRTDOMParser;

public class Tracker {
	private static Logger log = Logger.getLogger(Tracker.class);

	private PrintWriter out;
	static private boolean NotIncludeIgnorableWhiteSpaces = false;
	private XMLLocator locator;

	private DOMParser parser;
	private SCRTDOMParser parser2;

	public Tracker(String crfilename, String prfilename) {
		parser = new SCRTDOMParser();

		try {
			parser.setFeature(
					"http://apache.org/xml/features/dom/defer-node-expansion",
					false);
			parser.parse(crfilename);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return;
		} catch (org.xml.sax.SAXException e) {
			log.error(e.getMessage(), e);
			return;
		}

		parser2 = new SCRTDOMParser();

		try {
			parser2.setFeature(
					"http://apache.org/xml/features/dom/defer-node-expansion",
					false);
			parser2.parse(prfilename);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return;
		} catch (org.xml.sax.SAXException e) {
			log.error(e.getMessage(), e);
			return;
		}
	}

	/** Returns a sorted list of attributes. */
	private Attr[] sortAttributes(NamedNodeMap attrs) {

		int len = (attrs != null) ? attrs.getLength() : 0;
		Attr array[] = new Attr[len];
		for (int i = 0; i < len; i++) {
			array[i] = (Attr) attrs.item(i);
		}
		for (int i = 0; i < len - 1; i++) {
			String name = array[i].getNodeName();
			int index = i;
			for (int j = i + 1; j < len; j++) {
				String curName = array[j].getNodeName();
				if (curName.compareTo(name) < 0) {
					name = curName;
					index = j;
				}
			}
			if (index != i) {
				Attr temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}

		return (array);

	} // sortAttributes(NamedNodeMap):Attr[]

	public static void main(String argv[]) {
		Tracker tracker = new Tracker(argv[0], argv[1]);
		PropagationSet pr = new PropagationSet();
		Document crdoc = tracker.parser.getDocument();

		NodeList nl = crdoc.getElementsByTagName("ConfigurationRequirement");

		ConfigurationRequirementSet crs = new ConfigurationRequirementSet(nl);

		Document prdoc = tracker.parser2.getDocument();

		pr.extract(prdoc);
	}
}
