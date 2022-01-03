package XMLProcessing.SAX;

import WebPage.WebPage;
import XMLProcessing.AbstractWebPagesBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class WebPagesSaxBuilder extends AbstractWebPagesBuilder {
    /**
     * Set of webPages which were obtained from Xml.
     */
    private Set<WebPage> pages;
    private WebPageHandler handler = new WebPageHandler();
    private XMLReader reader;

    public WebPagesSaxBuilder() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            reader.setErrorHandler(new WebPageErrorHandler());
            reader.setContentHandler(handler);
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // log
        }
    }

    /**
     * Method obtaining all the WebPages as Set.
     *
     * @return Set of WebPages.
     */
    public Set<WebPage> getWebPages() {
        return pages;
    }

    /**
     * Method that builds the set based on the data from Xml document.
     *
     * @param filename Name of Xml file containing data.
     */
    public void buildSetWebPages(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        pages = handler.getWebPages();
    }
}
