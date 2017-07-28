package cscie259.project1.mf;


/**
 * A simplified version of org.xml.sax.helpers.DefaultHandler.
 *
 * Essentially, this class does nothing with the content
 * encountered by an XML parser.  It exists to facilitate
 * the development of more useful ContentHandlers by providing,
 * quite simply, a default implementation of ContentHandler's
 * methods.
 *
 * You MAY NOT modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 **/
public class DefaultHandler implements ContentHandler, ErrorHandler
{
    /**
     * Should be called immediately after a chunk of
     * character data is parsed.
     *
     * @param content   parsed character data
     */
    public void characters(String content) {}


    /**
     * Should be called immediately before an XML document is parsed.
     */
    public void startDocument() {}


    /**
     * Should be called immediately after a start tag is parsed.
     *
     * @param name  The opened element's name.
     * @param atts  A list of the opened element's attributes.
     */
    public void startElement(String name, Attributes atts) {}


    /**
     * Should be called immediately after an XML document is parsed.
     */
    public void endDocument() {}


    /**
     * Should be called immediately after an end tag is parsed.
     *
     * @param name closed element's name
     */
    public void endElement(String name) {}


    /**
     * Should be called immediately after a parsing error is encountered.
     *
     * @param exception exception related to the error
     */
    public void fatalError(Exception exception) {}
}
