package cscie259.project1.mf;

import java.util.LinkedList;

/**
 * A simplified version of org.xml.sax.helpers.AttributesImpl.
 *
 * An Attributes object stores zero or more attributes.
 *
 * You MAY modify this file to whatever extent you see fit, provided you do
 * not change the declarations of addAttribute, getLength, getName, or
 * getValue.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  YOUR NAME GOES HERE
 **/
public class Attributes {
	
	/**
	 * holds the attribute entries of the current element
	 * 
	 * one instance of this class for each xml-element having xml-attributes
	 * 
	 */
	private final LinkedList<AttributeEntry> attributes_;
	
	public Attributes() {
		attributes_ = new LinkedList<>();
	}
	
    /**
     * Adds a new attribute (i.e., name/value pair) to the collection.
     *
     * @param name  new attribute's name
     * @param value new attribute's value
     */
    public void addAttribute(String name, String value) {
    	this.attributes_.add(new AttributeEntry(name, value));
    }


    /**
     * Return the number of attributes in the list.
     *
     * @return the number of attributes in the list
     */
    public int getLength() {
        return this.attributes_.size();
    }


    /**
     * Return an attribute's name by index.
     *
     * @param index the attribute's index (zero-based).
     *
     * @return the attribute's name if available else null if the
     * attribute's name is not available or the index is out of range
     */
    public String getName(int index) {
        return this.attributes_.get(index).getKey();
    }


    /**
     * Return an attribute's value by index.
     *
     * @param index the attribute's index (zero-based)
     *
     * @return the attribute's value or null if the index is out of range
     */
    public String getValue(int index) {
        return this.attributes_.get(index).getValue();
    }
}
