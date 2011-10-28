package scRT.tracker;

import java.io.IOException;

import org.apache.log4j.Logger;

import scRT.parser.SCRTDOMParser;
import scRT.tracker.exception.PropagationException;

public class Tracker {
	private static Logger log = Logger.getLogger(Tracker.class);

	private ConfigurationRequirementSet crs;
	private PropagationSet ps;

	public Tracker(ConfigurationRequirementSet crs, PropagationSet ps) {
		this.crs = crs;
		this.ps = ps;
	}

	public boolean trackdown(ConfigurationValue input) {
		ConfigurationRequirement starter = crs.findCRbyCVID(input.getId());
		Iterable<Propagation> ps = starter.getPropagations();
		for (Propagation p : ps) {
			try {
				p.checkPropagation(input);
			} catch (PropagationException e) {
				log.debug(e.getMessage(), e);
				// FIXME Make Trace!!!
				return false;
			}
		}
		return true;
	}

	public static void main(String argv[]) {
		if (argv == null || argv.length < 3) {
			System.err
					.println("Usage: java scRT.tracker.Tracker CRFile PSFile input");
			return;
		}

		SCRTDOMParser crParser = parse(argv[0]);
		if (crParser == null) {
			System.err.println("Cannot load CRFile");
			return;
		}

		ConfigurationRequirementSet crs = ConfigurationRequirementSet
				.getInstance(crParser.getDocument().getDocumentElement());

		SCRTDOMParser psParser = parse(argv[1]);
		if (psParser == null) {
			System.err.println("Cannot load PSFile");
			return;
		}

		PropagationSet ps = new PropagationSet(psParser.getDocument()
				.getDocumentElement());

		Tracker tracker = new Tracker(crs, ps);
		// TODO Do something with tracker
	}

	private static SCRTDOMParser parse(String filePath) {
		SCRTDOMParser parser = new SCRTDOMParser();

		try {
			parser.setFeature(
					"http://apache.org/xml/features/dom/defer-node-expansion",
					false);
			parser.parse(filePath);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return null;
		} catch (org.xml.sax.SAXException e) {
			log.error(e.getMessage(), e);
			return null;
		}
		return parser;
	}
}
