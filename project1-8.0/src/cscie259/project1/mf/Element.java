package cscie259.project1.mf;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	
	private List<Attr> attributes_ = new LinkedList<>();
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
    public int getNodeType() {
        return Node.ELEMENT_NODE;
    }

    /**
     * Adds the given name and value pair as the attribute to this element
     * 
     * @param name the name of the attribute
     * @param value the value of the attribute
     */
    public void addAttribute(String name, String value) {
    	this.attributes_.add(new Attr(name, value));
    }
    
    /**
     * 
     * @return the attributes of this element as a list of Attr
     */
    public List<Attr> getAttribuesAsList() {
    	return Collections.unmodifiableList(this.attributes_);
    }
    
    /**
     * 
     * @return attributes of this element as Attributes
     */
    public Attributes getAttributesAsAttributes() {
    	Attributes newAttributes = new Attributes();
    	for(Attr attr : this.attributes_){
    		newAttributes.addAttribute(attr.getNodeName(), attr.getNodeValue());
    	}
    	return newAttributes;
    }
}
