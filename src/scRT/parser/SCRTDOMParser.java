package scRT.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.parsers.DOMParserImpl;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSInput;

public class SCRTDOMParser extends DOMParser {

	public SCRTDOMParser(){
		super();
	}
	
	public SCRTDOMParser(XMLParserConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SCRTDOMParser(SymbolTable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}



	public SCRTDOMParser(SymbolTable arg0, XMLGrammarPool arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}


}
