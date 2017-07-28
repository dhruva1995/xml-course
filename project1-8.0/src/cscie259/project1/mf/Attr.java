package cscie259.project1.mf;


/**
 * A simplified, non-interface version of org.w3c.dom.Attr.
 *
 * You MAY modify this file to whatever extent you see fit,
 * provided you retain the current declarations and definitions of,
 * at least, getNodeType and appendChild.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  YOUR NAME GOES HERE
 **/
public class Attr extends Node
{
    /**
     * Sets node's name and value.
     *
     * @param name  name for new attribute
     * @param value value for new attribute
     */
    Attr(String name, String value)
    {
        setNodeName(name);
        setNodeValue(value);
    }


    /**
     * Returns code (Node.ATTRIBUTE_NODE) signifying this node's type.
     *
     * @return Node.ATTRIBUTE_NODE
     */
    public int getNodeType()
    {
        return Node.ATTRIBUTE_NODE;
    }


    /**
     * Throws a RuntimeException, since attributes cannot have children.
     *
     * @param newChild node to be added as a child of this node
     */
    public void appendChild(Node newChild)
    {
        throw new RuntimeException("Error: attributes cannot have children");
    }
}
