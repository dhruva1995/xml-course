import java.io.*;

import java.text.DecimalFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Lecture 3's demonstration of
 * javax.xml.parsers.DocumentBuilder.
 *
 * @author  Computer Science E-259
 **/
public class DocumentBuilderDemo
{
    /**
     * Main driver.  Expects one command-line argument:
     * the name of the file to parse.
     *
     * @param argv [0] - filename
     */
    public static void main(String [] argv)
    {
        // ensure proper usage
        if (argv.length != 1)
        {
            System.out.println("Usage: java DocumentBuilderDemo filename");
            System.exit(1);
        }

        // grab filename
        String input = argv[0];

        // instantiate a DOM parser
        DocumentBuilder parser;
        try
        {
            DocumentBuilderFactory factory
             = DocumentBuilderFactory.newInstance();
            parser = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
            return;
        }

        // parse the document and grab the Document node
        Document doc;
        try
        {
            doc = parser.parse(input);
        }
        catch (SAXException e)
        {
            e.printStackTrace();
            return;
        }
        catch (IOException e)
        {
            System.err.println("Error reading file.\n");
            e.printStackTrace();
            return;
        }

        // Intantiate DOMDemo object
        DocumentBuilderDemo demo = new DocumentBuilderDemo();

        // calculate sale prices
        demo.slashPrices(doc.getDocumentElement());

        // serialize the modified document out to System.out
        XMLSerializer serializer =
         new XMLSerializer(System.out,
                           new OutputFormat("XML", "UTF-8", true));

        try
        {
            serializer.serialize(doc);
        }
        catch (IOException e)
        {
            System.err.println("Error reading file.\n");
            e.printStackTrace();
        }
    }


    /**
     * To every price element, encountered recursively, add a sale-price
     * attribute that reflects the price less a 20% discount.
     *
     * @param elt   element whose price is to be slashed
     */
    void slashPrices(Element elt)
    {
        // get element's children, if any
        NodeList children = elt.getChildNodes();

        // iterate through children
        for (int i = 0; i < children.getLength(); i++)
        {
			// get current child
            Node n = children.item(i);

            // we're only interested in Element children
            if (n.getNodeType() != Node.ELEMENT_NODE)
                continue;

            // if this child is a price element, slash it!
            if (n.getNodeName().equals("price"))
            {
                double price = 0.80 *
                 new Double(n.getFirstChild().getNodeValue()).doubleValue();
                DecimalFormat df = new DecimalFormat(".00");
               ((Element) n).setAttribute("sale-price", df.format(price));
            }

            // recurse on child
            slashPrices((Element) n);
        }
    }
}
