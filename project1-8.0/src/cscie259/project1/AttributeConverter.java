package cscie259.project1;

import javax.xml.parsers.SAXParserFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLFilterImpl;

import javax.xml.parsers.SAXParser;


/**
 * A program for converting elements' attributes to child elements.
 *
 * You MAY modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  YOUR NAME GOES HERE
 **/
public class AttributeConverter extends DefaultHandler implements LexicalHandler
{
	
	
	private final XMLSerializer serializer;
	
    private AttributeConverter (XMLSerializer serializer){
    	this.serializer = serializer;
    }
	
    /**
     * Main entry point to program.
     *
     * @param argv [0] - filename
     */
    public static void main(String[] argv)
    {
        // grab filename from command line
        if (argv.length != 1)
        {
            System.err.println(
                "usage: java " + "cscie259.project1.AttributeConverter "
                + "filename");
            System.exit(1);
        }
        String filename = argv[0];

        // create a serializer with which to pretty print our output
        XMLSerializer serializer = new XMLSerializer(
                System.out,
                new OutputFormat("XML", "UTF-8", true));

        // TODO
        try
        {
            // instantiate a SAX parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // instantiate our little demo handler
            AttributeConverter handler = new AttributeConverter(serializer);

            // parse the file
            parser.parse(filename, handler);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void endDocument() throws SAXException {
    	super.endDocument();
    	serializer.endDocument();
    }
    
    @Override
    public void startDocument() throws SAXException {
    	super.startDocument();
    	serializer.startDocument();
    }
    
    @Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		serializer.startElement(uri, localName, qName, null);
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String attrQname = attributes.getQName(i);
				String attrValue = attributes.getValue(i);
				String attrUri = attributes.getURI(i);
				String attrLocalName = attributes.getLocalName(i);
				this.startElement(attrUri, attrLocalName, attrQname, null);
				this.characters(attrValue.toCharArray(), 0, attrValue.length());
				this.endElement(attrUri, attrLocalName, attrQname);	
			}
		}
	}
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	super.endElement(uri, localName, qName);
    	serializer.endElement(localName);
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    	super.characters(ch, start, length);
    	serializer.characters(ch, start, length);
    }

	@Override
	public void startDTD(String name, String publicId, String systemId) throws SAXException {
		serializer.startDTD(name, publicId, systemId);
	}

	@Override
	public void endDTD() throws SAXException {
		serializer.endDTD();
		
	}

	@Override
	public void startEntity(String name) throws SAXException {
		serializer.startEntity(name);
	}

	@Override
	public void endEntity(String name) throws SAXException {
		serializer.endEntity(name);
	}

	@Override
	public void startCDATA() throws SAXException {
		serializer.startCDATA();
	}

	@Override
	public void endCDATA() throws SAXException {
		serializer.endCDATA();
	}

	@Override
	public void comment(char[] ch, int start, int length) throws SAXException {
		serializer.comment(ch, start, length);
	}
	
	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		super.processingInstruction(target, data);
		serializer.processingInstruction(target, data);
	}
	
}
