package scRT.parser;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public class SCRTDOMParser extends DOMParser {

	public SCRTDOMParser() {
		super();
	}

	public SCRTDOMParser(XMLParserConfiguration config) {
		super(config);
	}

	public SCRTDOMParser(SymbolTable symbolTable) {
		super(symbolTable);
	}

	public SCRTDOMParser(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
		super(symbolTable, grammarPool);
	}

}
