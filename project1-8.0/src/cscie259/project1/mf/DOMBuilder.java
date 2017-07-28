package cscie259.project1.mf;


/**
 * A simplified version of org.apache.xml.utils.DOMBuilder.
 *
 * A DOMBuilder is a ContentHandler that builds a DOM out of
 * SAX events.
 *
 * You MAY modify this file to whatever extent you see fit.
 * However, you MUST complete the implementation
 * of getDocument so that it returns a node of type
 * DOCUMENT_NODE whose descendants represent the contents
 * encountered by the XML parser.  Those descendants should be of
 * type ELEMENT_NODE, ATTRIBUTE_NODE, and/or TEXT_NODE.  And, clearly,
 * you MUST augment this class's implementation so that it actually
 * handles SAX events and builds a DOM.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  YOUR NAME GOES HERE
 **/
public class DOMBuilder extends DefaultHandler
{
    /**
     * The DOM's topmost node.
     */
    private Document doc_;


    /**
     * Returns document's topmost node (i.e., its sole Document node).
     *
     * @return document's topmost node
     */
    public Document getDocument()
    {
        return doc_;
    }


    // TODO
}
