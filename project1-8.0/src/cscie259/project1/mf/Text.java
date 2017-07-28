package cscie259.project1.mf;


/**
 * A simplified, non-interface version of org.w3c.dom.Text.
 *
 * You MAY NOT modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 **/
public class Text extends Node
{
    /**
     * Sets node's value.
     *
     * @param value value for new text node
     */
    public Text(String value)
    {
        setNodeValue(value);
    }


    /**
     * Throws a RuntimeException, since text nodes cannot have children.
     *
     * @param newChild node to be added as a child of this node
     */
    public void appendChild(Node newChild)
    {
        throw new RuntimeException("Error: text nodes cannot have children");
    }


    /**
     * Returns code (Node.TEXT) signifying this node's type.
     *
     * @return Node.TEXT
     */
    public int getNodeType()
    {
        return Node.TEXT_NODE;
    }
}
