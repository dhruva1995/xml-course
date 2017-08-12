package cscie259.project1.mf;

import java.util.LinkedList;
import java.util.List;


/**
 * A simplified version of org.w3c.dom.Node.
 *
 * You MAY NOT modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 **/
public abstract class Node
{
    /**
     * Child elements are to be stored in a List.
     */
    private List<Node> children_ = new LinkedList<Node>();


    /**
     * An Attr or Element node's name.
     */
    private String name_ = null;


    /**
     * A reference to this node's parent, if any.
     */
    private Node parent_ = null;


    /**
     * An Attr or Text node's value.
     */
    private String value_ = null;


    /**
     * Short code identifying the type of a Document node.
     */
    public static final int DOCUMENT_NODE = 0;


    /**
     * Short code identifying the type of an Element node.
     */
    public static final int ELEMENT_NODE = 1;


    /**
     * Short code identifying the type of a Attr node.
     */
    public static final int ATTRIBUTE_NODE = 2;


    /**
     * Short code identifying the type of a Text node.
     */
    public static final int TEXT_NODE = 3;


    /**
     * Appends new child to node.
     *
     * @param newChild  child to be added
     */
    public void appendChild(Node newChild)
    {
        newChild.parent_ = this;
        children_.add(newChild);
    }


    /**
     * Gets node's children.
     *
     * @return  List of node's children.
     */
    public List<Node> getChildNodes()
    {
        return children_;
    }


    /**
     * Gets node's name.
     *
     * @return  node's name.
     */
    public String getNodeName()
    {
        return name_;
    }


    /**
     * Returns code signifying this node's type.
     *
     * @return  node's type
     */
    public abstract int getNodeType();


    /**
     * Gets node's value.
     *
     * @return  node's value.
     */
    public String getNodeValue()
    {
        return value_;
    }


    /**
     * Returns node's parent.
     *
     * @return  node's parent
     */
    public Node getParentNode()
    {
        return parent_;
    }


    /**
     * Sets node's name.
     *
     * @param   name    node's name
     */
    public void setNodeName(String name)
    {
        name_ = name;
    }


    /**
     * Sets node's name.
     *
     * @param   value    node's value
     */
    public void setNodeValue(String value)
    {
        value_ = value;
    }
}
