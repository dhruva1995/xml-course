package cscie259.project1;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;


/**
 * A program for converting elements' attributes to child elements.
 *
 * You MAY modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  YOUR NAME GOES HERE
 **/
public class AttributeConverter
{
    /**
     * Main entry point to program.
     *
     * @param argv [0] - filename
     */
    public static void main(String[] argv)
    {
        // grab filename from command line
        if (argv.length != 1)
        {
            System.err.println(
                "usage: java " + "cscie259.project1.AttributeConverter "
                + "filename");
            System.exit(1);
        }
        String filename = argv[0];

        // create a serializer with which to pretty print our output
        XMLSerializer serializer = new XMLSerializer(
                System.out,
                new OutputFormat("XML", "UTF-8", true));

        // TODO
    }
}
