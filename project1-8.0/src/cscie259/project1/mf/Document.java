package cscie259.project1.mf;

import java.util.List;


/**
 * A simplified, non-interface version of org.w3c.dom.Document.
 *
 * An object of this class represents a DOM's topmost node.
 *
 * You MAY NOT modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 **/
public class Document extends Node
{
    /**
     * Returns code (Node.DOCUMENT_NODE) signifying this node's type.
     *
     * @return Node.DOCUMENT_NODE
     */
    public int getNodeType()
    {
        return Node.DOCUMENT_NODE;
    }


    /**
     * Returns child node that is the root element of the document.
     *
     * @return child node that is the root element of the document
     */
    public Element getDocumentElement()
    {
        // storage for node
        Element elt;

        // attempt to retrieve root element
        List<Node> children = getChildNodes();
        elt = (children != null) ? (Element) children.get(0) : null;

        // return node, if any
        return elt;
    }
}
