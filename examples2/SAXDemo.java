import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Lecture 2's demonstration of SAX 2.0.2.
 *
 * @author  Computer Science E-259
 **/

public class SAXDemo extends DefaultHandler
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
            System.out.println("Usage: java SAXDemo filename");
            System.exit(1);
        }

        // grab filename
        String input = argv[0];

        try
        {
            // instantiate a SAX parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // instantiate our little demo handler
            SAXDemo handler = new SAXDemo();

            // parse the file
            parser.parse(input, handler);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Report a startElement event.
     *
     * @param   uri namespace
     * @param   localName   name of element, sans namespace
     * @param   qName       name of element, with namespace
     * @param   attributes  element's collection of attributes
     *
     * @throws  SAXException    general SAX error or warning
     */

    public void startElement(String uri, String localName,
                             String qName, Attributes atts)
     throws SAXException
    {
        System.out.print("startElement(\"" + qName + "\", {");
        for (int i = 0; i < atts.getLength(); i++)
        {
            System.out.print("(\"" + atts.getQName(i) + "\", \"" +
                             atts.getValue(i) + "\")");
            if (i != atts.getLength() - 1)
                System.out.print(", ");
        }
        System.out.println("});");
    }


    /**
     * Report a characters event.
     *
     * @param   ch      characters
     * @param   start   start position in the character array
     * @param   length  number of characters to use from the character array
     *
     * @throws  SAXException    general SAX error or warning
     */

    public void characters(char[] ch, int start, int length)
     throws SAXException
    {
        System.out.println("characters(\"" + new String(ch, start, length) +
                           "\");");
    }


    /**
     * Report an endElement event.
     *
     * @param   uri         namespace
     * @param   localName   name of element, sans namespace
     * @param   qName       name of element, with namespace
     *
     * @throws  SAXException    general SAX error or warning
     */

    public void endElement(String uri, String localName, String qName)
     throws SAXException
    {
        System.out.println("endElement(\"" + qName + "\");");
    }


    /**
     * Report a startDocument event.
     */

    public void startDocument() throws SAXException
    {
        System.out.println("\nstartDocument();");
    }


    /**
     * Report an endDocument event.
     *
     * @throws  SAXException    general SAX error or warning
     */

    public void endDocument() throws SAXException
    {
        System.out.println("endDocument();\n");
    }
}
