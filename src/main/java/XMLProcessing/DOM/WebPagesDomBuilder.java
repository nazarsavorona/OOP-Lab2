package XMLProcessing.DOM;

import WebPage.WebPage;
import WebPage.WebPageFactory;
import WebPage.WebPageType;
import XMLProcessing.AbstractWebPagesBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebPagesDomBuilder extends AbstractWebPagesBuilder {
    /**
     * Set of webPages which were obtained from Xml.
     */
    private Set<WebPage> webPages;
    private DocumentBuilder docBuilder;

    public WebPagesDomBuilder() {
        webPages = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Set<WebPage> getWebPages() {
        return webPages;
    }

    /**
     * Method that builds the set based on the data from Xml document.
     * @param filename Name of Xml file containing data.
     */
    public void buildSetWebPages(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();

            NodeList webPagesList = root.getElementsByTagName("page");
            for (int i = 0; i < webPagesList.getLength(); i++) {
                Element webPageElement = (Element) webPagesList.item(i);
                WebPage webPage = buildWebPage(webPageElement);
                webPages.add(webPage);
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to build single WebPage from data of webPage Element.
     * @param webPageElement One of WebPage elements.
     * @return WebPage object.
     */
    private WebPage buildWebPage(Element webPageElement) {
        WebPageType webPageType = WebPageType.valueOf(webPageElement.getAttribute("type").toUpperCase());
        WebPage webPage = WebPageFactory.getInstance(webPageType);

        String idString = webPageElement.getAttribute("id").toUpperCase().replace("ID-", "");
        
        webPage.setId(Integer.parseInt(idString));
        webPage.setNeedsAuthorization(Boolean.parseBoolean(webPageElement.getAttribute("authorisation")));

        webPage.setTitle(getElementTextContent(webPageElement, "title"));
        Element charsElement = (Element) webPageElement.getElementsByTagName("chars").item(0);

        String mail = charsElement.getAttribute("mail");
        String news = charsElement.getAttribute("news");
        String paid = charsElement.getAttribute("paid");
        String archive = charsElement.getAttribute("archive");

        if (!mail.equals("")) {
            webPage.setMailAvailable(Boolean.parseBoolean(mail));
        }

        if (!news.equals("")) {
            webPage.setNewsAvailable(Boolean.parseBoolean(news));
        }

        if (!paid.equals("")) {
            webPage.setPaid(Boolean.parseBoolean(paid));
        }

        if (!archive.equals("")) {
            webPage.setArchivesAvailable(Boolean.parseBoolean(archive));
        }

        NodeList pollList = webPageElement.getElementsByTagName("poll");
        if (pollList.getLength() > 0) {
            webPage.setArePollsAvailable(true);
            webPage.setArePollsNeedAuthorization(Boolean.parseBoolean(((Element) pollList.item(0)).getAttribute(
                    "authorisation")));
        } else {
            webPage.setArePollsAvailable(false);
        }

        return webPage;
    }


    /**
     *
     * @param element Element form xml containing data.
     * @param elementName Name of element.
     * @return String which is a content of element.
     */
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        return nList.item(0).getTextContent();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: <XML FILE PATH>");
            System.exit(-1);
        }
        WebPagesDomBuilder domBuilder = new WebPagesDomBuilder();
        domBuilder.buildSetWebPages(args[0]);
        System.out.println(domBuilder.getWebPages());
    }
}
