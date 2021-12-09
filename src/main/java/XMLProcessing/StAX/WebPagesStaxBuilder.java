package XMLProcessing.StAX;

import WebPage.WebPage;
import WebPage.WebPageFactory;
import WebPage.WebPageType;
import WebPage.WebPageXmlTag;
import XMLProcessing.AbstractWebPagesBuilder;

import javax.print.attribute.Attribute;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebPagesStaxBuilder extends AbstractWebPagesBuilder {
    /**
     * Set of webPages which were obtained from Xml.
     */
    private Set<WebPage> webPages;
    private XMLInputFactory inputFactory;

    public WebPagesStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        webPages = new HashSet<>();
    }

    public Set<WebPage> getWebPages() {
        return webPages;
    }

    /**
     * Method that builds the set based on the data from Xml document.
     * @param filename Name of Xml file containing data.
     */
    public void buildSetWebPages(String filename) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(WebPageXmlTag.PAGE.getValue())) {
                        WebPage webPage = buildWebPage(reader);
                        webPages.add(webPage);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to build single WebPage from xml stream reader data.
     * @param reader xml stream reader.
     * @return WebPage object.
     * @throws XMLStreamException
     */
    private WebPage buildWebPage(XMLStreamReader reader) throws XMLStreamException {
        WebPageType webPageType = WebPageType.valueOf(
                reader.getAttributeValue(null, WebPageXmlTag.TYPE.getValue()).toUpperCase());
        WebPage webPage = WebPageFactory.getInstance(webPageType);

        String idString = reader.getAttributeValue(null, WebPageXmlTag.ID.getValue()).replace("ID-","");

        webPage.setId(Integer.parseInt(idString));

        webPage.setNeedsAuthorization(Boolean.parseBoolean(
                reader.getAttributeValue(null, WebPageXmlTag.AUTHORIZATION.getValue())));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (WebPageXmlTag.valueOf(name.toUpperCase())) {
                        case TITLE -> webPage.setTitle(getXMLText(reader));
                        case POLL -> webPage.setArePollsAvailable(true);
                        case AUTHORIZATION -> webPage.setArePollsNeedAuthorization(Boolean.parseBoolean(reader.getAttributeValue(null, WebPageXmlTag.AUTHORIZATION.getValue())));
                        case CHARS -> {
                            String attribute = reader.getAttributeValue(null, WebPageXmlTag.MAIL.getValue());
                            if (attribute != null) {
                                webPage.setMailAvailable(Boolean.parseBoolean(attribute));
                            }

                            attribute = reader.getAttributeValue(null, WebPageXmlTag.ARCHIVE.getValue());
                            if (attribute != null) {
                                webPage.setArchivesAvailable(Boolean.parseBoolean(attribute));
                            }

                            attribute = reader.getAttributeValue(null, WebPageXmlTag.PAID.getValue());
                            if (attribute != null) {
                                webPage.setPaid(Boolean.parseBoolean(attribute));
                            }

                            attribute = reader.getAttributeValue(null, WebPageXmlTag.NEWS.getValue());
                            if (attribute != null) {
                                webPage.setNewsAvailable(Boolean.parseBoolean(attribute));
                            }
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (WebPageXmlTag.valueOf(name.toUpperCase()) == WebPageXmlTag.CHARS) {
                        return webPage;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <page>");

    }

    /**
     * Method to get xml text from xml stream reader.
     * @param reader xml stream reader.
     * @return String (xml text).
     * @throws XMLStreamException
     */
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: <XML FILE PATH>");
            System.exit(-1);
        }

        WebPagesStaxBuilder staxBuilder = new WebPagesStaxBuilder();
        staxBuilder.buildSetWebPages(args[0]);
        System.out.println(staxBuilder.getWebPages());
    }
}
