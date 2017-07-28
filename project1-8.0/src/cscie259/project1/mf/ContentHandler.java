package cscie259.project1.mf;


/**
 * A simplified version of org.xml.sax.ContentHandler.
 *
 * Essentially, any class that implements this interface
 * can "handle the content" encountered by an XML parser.
 *
 * You MAY NOT modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 **/
public interface ContentHandler
{
    /**
     * Should be called immediately after a chunk of
     * character data is parsed.
     *
     * @param content parsed character data
     */
    void characters(String content);


    /**
     * Should be called immediately after an XML document is parsed.
     */
    void endDocument();


    /**
     * Should be called immediately after an end tag is parsed.
     *
     * @param name closed element's name
     */
    void endElement(String name);


    /**
     * Should be called immediately before an XML document is parsed.
     */
    void startDocument();


    /**
     * Should be called immediately after a start tag is parsed.
     *
     * @param name opened element's name.
     * @param atts list of the opened element's attributes
     */
    void startElement(String name, Attributes atts);
}
