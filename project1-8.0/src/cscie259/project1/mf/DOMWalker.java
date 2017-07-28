package cscie259.project1.mf;

import java.util.Iterator;


/**
 * A class whose sole purpose in life is to walk your DOM.
 *
 * You MAY modify this file so that it handles your
 * implementation of attributes.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  YOUR NAME GOES HERE
 **/
public abstract class DOMWalker
{
    /**
     * Initiates a walk on given document, passing SAX events to handler.
     *
     * @param doc     document's topmost node
     * @param handler DefaultHandler for SAX events
     */
    public static void walk(Document doc, DefaultHandler handler)
    {
        handler.startDocument();
        visit(doc.getDocumentElement(), handler);
        handler.endDocument();
    }


    /**
     * Recursively visits each node in the DOM, passing SAX events to handler.
     * You MUST complete the implementation of this method so that it
     * handles your implementation of attributes.
     *
     * @param cur     node currently being visited
     * @param handler DefaultHandler for SAX events
     */
    private static void visit(Node cur, DefaultHandler handler)
    {
        switch (cur.getNodeType())
        {
            case Node.TEXT_NODE:
                handler.characters(cur.getNodeValue());

                break;

            case Node.ELEMENT_NODE:

                // PROVIDE SUPPORT FOR YOUR IMPLEMENTATION OF
                // ATTRIBUTES BELOW; IN OTHER WORDS, REPLACE
                // null BELOW WITH A REFERENCE TO AN Attributes OBJECT
                // STORING THE CURRENT ELEMENT'S COLLECTION
                // OF ATTRIBUTES
                handler.startElement(cur.getNodeName(), null);

                Iterator iter = cur.getChildNodes().iterator();

                while (iter.hasNext())
                    visit((Node) iter.next(), handler);

                handler.endElement(cur.getNodeName());

                break;

            default:
                throw new RuntimeException(
                    "Type " + cur.getNodeType() + " not handled");
        }
    }
}
