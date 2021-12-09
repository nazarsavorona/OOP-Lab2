package XMLProcessing.SAX;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Class to test Sax parser functionality.
 */
public class SaxConsoleMain {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: <XML FILE PATH>");
            System.exit(-1);
        }
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new ConsoleWebPageHandler());
            reader.setErrorHandler(new WebPageErrorHandler());
            reader.parse(args[0]);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
