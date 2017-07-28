package cscie259.project1.mf;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;


/**
 * A driver for testing your code.
 *
 * You MAY modify this file to whatever extent you see fit.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  YOUR NAME GOES HERE
 **/
public class Tester
{
    /**
     * Default constructor is private so that this utility
     * class cannot be instantiated.
     */
    private Tester() {}


    /**
     * Main driver.  Expects two command-line arguments: the name of the file
     * to be parsed, followed by a test number.  Valid test numbers, at
     * present, are 1 and 2.  1 invokes testing of XMLParser (and
     * related classes) and XMLSerializer.  2 invokes testing of
     * XMLParser (and related classes), DOMBuilder,
     * DOMWalker, and XMLSerializer.
     *
     * @param argv [0] - filename, [1] - testnumber
     */
    public static void main(String[] argv)
    {
        // enforce proper usage
        if (argv.length < 2)
        {
            System.out.println(
                "usage: java cscie259.project1.mf.Tester "
                + "filename testnumber");

            return;
        }

        // execute requested test case
        switch (Integer.parseInt(argv[1]))
        {
            // Test XMLParser (and related classes) and
            // XMLSerializer
            case 1:

                // instantiate a parser
                XMLParser p1 = new XMLParser();

                // instantiate a BufferedWriter for System.out
                BufferedWriter bw1 = new BufferedWriter(
                        new OutputStreamWriter(System.out));

                // by default, don't ask XMLSerializer to pretty-print;
                // rely on input file's own whitespace, if any
                XMLSerializer s1 = new XMLSerializer(bw1, false);

                // try to parse the file, serializing in the process!
                p1.parse(argv[0], s1);

                break;

            // Test XMLParser (and related classes), DOMBuilder,
            // DOMWalker, and XMLSerializer
            case 2:

                // instantiate a parser
                XMLParser p2 = new XMLParser();

                // instantiate a DOMBuilder
                DOMBuilder db = new DOMBuilder();

                // try to parse the file, building a DOM in the process!
                p2.parse(argv[0], db);

                // grab the DOM's topmost node
                Document doc = db.getDocument();

                // instantiate a BufferedWriter for System.out
                BufferedWriter bw2 = new BufferedWriter(
                        new OutputStreamWriter(System.out));

                // by default, don't ask XMLSerializer to pretty-print;
                // rely on input file's own whitespace, if any
                XMLSerializer s2 = new XMLSerializer(bw2, false);

                // walk the DOM, serializing in the process!
                DOMWalker.walk(doc, s2);

                break;

            default:
                System.out.println("Error: testnumber must be 1 or 2");
        }
    }
}
