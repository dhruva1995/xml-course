package cscie259.project1.mf;


/**
 * A simplified, non-interface version of org.w3c.dom.Element.
 *
 * You MAY modify this file, provided you do not change the
 * declarations or definitions of the constructor and getNodeType.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  YOUR NAME GOES HERE
 **/
public class Element extends Node
{
    /**
     * Sets node's name.
     *
     * @param   name    name for new element
     */
    public Element(String name)
    {
        setNodeName(name);
    }


    /**
     * Returns code (Node.ELEMENT_NODE) signifying this node's type.
     *
     * @return Node.ELEMENT_NODE
     */
    public int getNodeType()
    {
        return Node.ELEMENT_NODE;
    }
}
