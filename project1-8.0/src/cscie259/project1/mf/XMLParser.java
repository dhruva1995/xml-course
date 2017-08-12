package cscie259.project1.mf;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * A simplified XML parser.  In essence, this class supports a subset
 * of the functionality collectively offered by javax.xml.parsers.SAXParser
 * and javax.xml.parsers.DocumentBuilder.
 *
 * You MAY modify this file.
 *
 * @author  Computer Science E-259
 * @version 8.0
 *
 * @author  Dhruva Chandra
 **/
public class XMLParser
{
    /**
     * Storage for input file's contents.
     */
    private String data_;


    /**
     * A reference to the currently registered DefaultHandler.
     */
    private DefaultHandler handler_;


    /**
     * Index of our current location in input file's contents.
     */
    private int index_ = 0;


    /**
     * Returns true if the next characters in the stream are the beginning
     * of an element's end tag.
     *
     * @return true iff next characters in the stream are the beginning
     * of an element's end tag
     */
    protected boolean isEndTag()
    {
        return (data_.charAt(index_) == '<')
        && (data_.charAt(index_ + 1) == '/');
    }


    /**
     * Returns true if the next character in the stream is the beginning
     * of an element's start tag.
     *
     * @return true iff next character in the stream is the beginning
     * of an element's start tag
     */
    protected boolean isStartTag()
    {
        return data_.charAt(index_) == '<';
    }


    /**
     * Parses the specified file, if possible, passing SAX events
     * to given handler.
     *
     * @param filename name of file whose contents are to be parsed
     * @param handler  DefaultHandler for SAX events
     */
    public void parse(String filename, DefaultHandler handler)
    {
        // initialize to clean up from any previous parse
        data_ = "";
        index_ = 0;
        handler_ = handler;

        // attempt to open file and read contents into local storage
        try
        {
            File f = new File(filename);
            int filesize = (int) f.length();
            byte[] filebytes = new byte[filesize];
            DataInputStream in = new DataInputStream(new FileInputStream(f));
            in.readFully(filebytes);
            in.close();
            data_ = new String(filebytes);
        }
        catch (IOException E)
        {
            handler_.fatalError(new Exception("Error: could not read file"));
            return;
        }
        
        // parse the document; hopefully there's a root element!
        handler_.startDocument();
        skipConsecutiveWhitespaces();
        //check if file has any non-whitespace character.
		if (index_ != data_.length()) {
			readElement();
		}
        handler_.endDocument();
    }


	/**
	 * Skips over consecutive whitespace characters from current character pointed by index_
	 */
	protected void skipConsecutiveWhitespaces() {
		while (index_ < data_.length() && Character.isWhitespace(data_.charAt(index_))) {
			index_++;
		}
	}


	/**
     * Parses an element and its content.
     */
    protected void readElement()
    {
        if (!isStartTag())
        {
            handler_.fatalError(new RuntimeException("Error: expecting " +
                                                     "start of element"));
            return;
        }

        AtomicBoolean emptyElement = new AtomicBoolean(false);
        
        // parse end tag
        String name = readStartTag(emptyElement);

        //Process Empty Element and return.
		if (emptyElement.get()) {
			handler_.endElement(name);
			return;
		}
		
		skipConsecutiveWhitespaces();
        
        // keep reading in more elements and text until an end tag
        // is encountered	
		while (!isEndTag()) {
			if (isStartTag()) {
				readElement();
				skipConsecutiveWhitespaces();
			} else {
				readText();
			}
		}
		// parse end tag, ensuring it matches most current start tag
		readEndTag(name);
    }    
    

    /**
     * Parses an end tag, ensuring its name matches currently opened
     * element's name.
     *
     * @param checkName currently opened element's name with which
     * end tag should be compared
     */
    protected void readEndTag(String checkName)
    {
        // read starting <
        index_++;

        // read /
        index_++;

        // read name
        String name = "";
		
		while (data_.charAt(index_) != '>' && isNotWhiteSpace()) {
        	if(validateCharInName(data_.charAt(index_))){
        		name += data_.charAt(index_);
        		index_++;
        	} else {
        		handler_.fatalError(new RuntimeException("Illegal character inside the name of a tag at " + index_));
        	}
        };

        //ETag ::= '<' '/' Name S* '>' 
        //remove S* for above case.
        skipConsecutiveWhitespaces();
        
        if(data_.charAt(index_) != '>') {
        	handler_.fatalError(new RuntimeException("Error : expecting '>' at the last of end tag at " + index_));
        }
        
        // read ending >
        index_++;

        // ensure content is well-formed
        if (!checkName.equals(name))
        {
            handler_.fatalError(new RuntimeException("Error: expecting " +
                                                     "closing tag for " + 
                                                     checkName + " but is " + name));
            return;
        }

        // pass this SAX event to handler
        handler_.endElement(name);
    }


    /**
     * Parses a start tag, returning opened element's name.
     * @param emptyElement 
     *
     * @return name of element
     */
    protected String readStartTag(AtomicBoolean emptyElement)
    {
        

        // Read starting <
        index_++;

        String name = "";
        
        // Read name
		while (data_.charAt(index_) != '>' && data_.charAt(index_) != '/' && isNotWhiteSpace()) {
        	if(validateCharInName(data_.charAt(index_))){
        		name += data_.charAt(index_);
        		index_++;
        	} else {
        		handler_.fatalError(new RuntimeException("Illegal character inside the name of a tag at " + index_));
        	}
        };
        
        // skip white-spaces after name like <name    attr1="value1">
        skipConsecutiveWhitespaces();
        
        Attributes attributes = new Attributes();
        while(data_.charAt(index_) != '>' && data_.charAt(index_) != '/') {
        	
        	//Read attribute name
        	String attributeName = "";
        	while (data_.charAt(index_) != '=' && isNotWhiteSpace()){
            	if(validateCharInName(data_.charAt(index_))){
            		attributeName += data_.charAt(index_);
            		index_++;
            	} else {
            		handler_.fatalError(new RuntimeException("Illegal character inside the name of a attribute at " + index_));
            	}
            }
        	
        	// S* =
            skipConsecutiveWhitespaces();
            if(data_.charAt(index_) != '=') {
            	handler_.fatalError(new RuntimeException("Error:  character not '=' after attribute name at " + index_));
            }
            
            //Read =
            index_++;
            
            //= S*
            skipConsecutiveWhitespaces();
            
            //"value1"
            if(data_.charAt(index_) != '"') {
            	handler_.fatalError(new RuntimeException("Illegal character not '\"' at starting of attribute value at " + index_));
            }
            
            //Read "
            index_++;
            String attributeValue = "";
            //Read attribute value
            while (data_.charAt(index_) != '"') {
            	if(validateCharInAttributeValue(data_.charAt(index_))){
            		attributeValue += data_.charAt(index_);
            		index_++;
            	} else {
            		handler_.fatalError(new RuntimeException("Illegal character '<' or '\"' inside the attribute value at " + index_));
            	}
            }
            
            //Read "
            index_++;
            
            attributes.addAttribute(attributeName, attributeValue);
            
            //Remove any space after attribute pair like  attr1="value1"    attr2="value2"
            skipConsecutiveWhitespaces();
        }
        
        if (data_.charAt(index_) == '>') {
        	// Read ending >
        	emptyElement.set(false);
            index_++;
        } else if (data_.charAt(index_) == '/' && data_.charAt(index_ + 1) == '>') {
        	// Read ending />
        	index_ += 2;
        	emptyElement.set(true);
        } else {
        	handler_.fatalError(new RuntimeException("Illegal character either should be '>' or '/>' at " + index_));
        }
        
        //if no attributes are present then set attributes to null
        if (attributes.getLength() == 0) {
        	attributes = null;
        }
        handler_.startElement(name, attributes);

        // return this element's name, for later comparision
        // with an end tag
        return name;
    }

    
    /**
     * @param charToExamine
     * 
     * AttValue can be zero or more characters excluding '&lt;' and '"' from course docs.
     * 
     * @return true if charToExamine is in {'&lt;', '"'} or else false
     */
    private boolean validateCharInAttributeValue(char charToExamine) {
		return charToExamine != '<' && charToExamine != '"';
	}


    /**
     * @param charToExamine
     * 
     * Name is now one or more letters, numbers, hyphens, periods, and underscores
     * 
     * Common method for validating the name in both tag name and attribute name.
     * 
     * @return true if charToExamine is one of letters, numbers, hyphens, periods, and underscores or else false.
     */
    private boolean validateCharInName(char charToExamine) {
    	return Character.isLetter(charToExamine) 
    			|| Character.isDigit(charToExamine) 
    			|| charToExamine == '-' 
    			|| charToExamine == '.' 
    			|| charToExamine == '_';
    }


    
	/**
	 * @return true if the character pointed by index_ is a non-whitespace character or else false
	 */
	protected boolean isNotWhiteSpace() {
		return isNotWhiteSpace(data_.charAt(index_));
	}

	/**
	 * @return true if the character pointed by index_ (instance variable) is a white space character.
	 */
	protected boolean isWhiteSpace(){
		return !this.isNotWhiteSpace();
	}
	
	/**
	 * @param charToExamine the input character to examine
	 * @return true if chatToExamine is a non-whitespace character or else false
	 */
	private boolean isNotWhiteSpace(char charToExamine) {
		return !Character.isWhitespace(charToExamine);
	}


	/**
     * Parses character data.
     */
    protected void readText()
    {
        // start character data from scratch
        String content = "";
        
        // accumulate characters until next tag
		while (data_.charAt(index_) != '<') {
			content += data_.charAt(index_);
			index_++;
		}
        content = removeIgnorableWhiteSpaceCharactersAtStart(content);
		content = removeIgnorableWhiteSpaceCharactersAtEnd(content);
		
        // pass this SAX event to handler
        handler_.characters(content);
    }

    /**
     * 
     * @param content the input
     * @return the input passed by removing ignorable-whitespace characters at the end of the input
     */
    private String removeIgnorableWhiteSpaceCharactersAtEnd(String content) {
    	int indexOfLastNonWhiteSpaceCharacter = content.length() - 1;
    	for(int i = content.length() - 1; i >= 0; i--){
    		if(!Character.isWhitespace(content.charAt(i))){
    			indexOfLastNonWhiteSpaceCharacter = i;
    			break;
    		}
    	}
    	return content.substring(0, indexOfLastNonWhiteSpaceCharacter + 1);
	}

    /**
     * 
     * @param content the input
     * @return the input passed by removing ignorable-whitespace characters at the start of the input
     */
	private String removeIgnorableWhiteSpaceCharactersAtStart(String content) {
    	int indexOfFirstNonWhiteSpaceCharacter = 0;
    	for (int i = 0; i < content.length(); i++){
    		if(!Character.isWhitespace(content.charAt(i))){
    			indexOfFirstNonWhiteSpaceCharacter = i;
    			break;
    		}
    	}
		return content.substring(indexOfFirstNonWhiteSpaceCharacter);
	}
}
