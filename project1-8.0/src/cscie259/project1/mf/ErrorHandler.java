package cscie259.project1.mf;


/**
 * A simplified version of org.xml.sax.ErrorHandler.
 *
 * Essentially, any class that implements this interface
 * can "handle the errors" encountered by an XML parser.
 *
 * You MAY NOT modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 **/
public interface ErrorHandler
{
    /**
     * Should be called immediately after a parsing error is encountered.
     *
     * @param exception exception related to the error
     */
    void fatalError(Exception exception);
}
