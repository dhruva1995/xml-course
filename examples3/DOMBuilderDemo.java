import cscie259.project1.mf.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;


/**
 * Lecture 3's demonstration of Project 1's DOMBuilder.
 *
 * @author  Computer Science E-259
 **/

public class DOMBuilderDemo
{
    // counters
    private int texts_      = 0;
    private int elements_   = 0;


    /**
     * Main driver.  Expects one command-line argument:
     * the name of the file to parse.
     *
     * @param argv [0] - filename
     */

    public static void main(String [] argv)
    {
        // grab filename
        String input = argv[0];

        try
        {
            // instantiate My First XML Parser and a DOMBuilder
            XMLParser p = new XMLParser();
            DOMBuilder db = new DOMBuilder();

            // parse the document
            p.parse(input, db);

            // grab the Document node
            Document doc = db.getDocument();

            // instantiate an object of this class
            DOMBuilderDemo demo = new DOMBuilderDemo();

            // count its Element and Text nodes
            try
            {
                demo.visit(doc.getDocumentElement());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            // report results
            System.out.println("There were " + demo.elements_ +
                               " elements.");
            System.out.println("There were " + demo.texts_ +
                               " text nodes.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Recursively count Element and Text nodes.
     *
     * @param n node to examine and then recurse on
     */

    public void visit(Node n)
    {
        switch (n.getNodeType())
        {
            // if this is a Text node, record such
            case Node.TEXT_NODE:
                texts_++;
                break;

            // if this is an Element node, record such, and then
            // recurse on any children
            case Node.ELEMENT_NODE:
                elements_++;
                List children = n.getChildNodes();
                Iterator it = children.iterator();
                while (it.hasNext())
                    visit((Node) it.next());

            break;
        }
    }
}
