import WebPage.*;
import XMLProcessing.AbstractWebPagesBuilder;
import XMLProcessing.WebPageBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestWebPage {
    @Test
    public void getRightInstance() {
        WebPage advertisement = WebPageFactory.getInstance(WebPageType.ADVERTISEMENT);
        WebPage news = WebPageFactory.getInstance(WebPageType.NEWS);

        WebPage mirror = WebPageFactory.getInstance(WebPageType.MIRROR);
        WebPage portal = WebPageFactory.getInstance(WebPageType.PORTAL);

        testAdvertisement(advertisement);
        testNews(news);
    }

    private void testNews(WebPage news) {
        news.setTitle("The Times");
        news.setPaid(true);
        news.setNewsAvailable(true);
        news.setId(1);

        Assert.assertEquals(news.toString(), "NewsWebPage{\n" +
                "id=1\n" +
                "title='The Times'\n" +
                "isMailAvailable=false\n" +
                "isNewsAvailable=true\n" +
                "needsAuthorization=false\n" +
                "arePollsAvailable=false\n" +
                "arePollsNeedAuthorization=false\n" +
                "isPaid=true\n" +
                "}");
    }

    private void testAdvertisement(WebPage advertisement) {
        advertisement.setTitle("Advertisement");
        advertisement.setArePollsAvailable(false);
        advertisement.setArePollsNeedAuthorization(true);
        advertisement.setId(0);

        Assert.assertEquals(advertisement.toString(), "AdvertisementWebPage{\n" +
                "id=0\n" +
                "title='Advertisement'\n" +
                "needsAuthorization=false\n" +
                "arePollsAvailable=false\n" +
                "arePollsNeedAuthorization=false\n" +
                "isPaid=false\n" +
                "}");
    }

    @Test
    public void testReadFromFile() {
        WebPage mirror = WebPageFactory.getInstance(WebPageType.MIRROR);
        mirror.setId(0);
        mirror.setNeedsAuthorization(true);
        mirror.setMailAvailable(false);
        mirror.setPaid(false);
        mirror.setArePollsNeedAuthorization(false);
        mirror.setArchivesAvailable(true);
        mirror.setTitle("Mirror Page");
        mirror.setArePollsAvailable(true);

        Assert.assertEquals(mirror.toString(), readFromFile("dom").toString());
        Assert.assertEquals(mirror.toString(), readFromFile("stax").toString());
        Assert.assertEquals(mirror.toString(), readFromFile("sax").toString());
    }

    private WebPage readFromFile(String type) {
        AbstractWebPagesBuilder builder = WebPageBuilderFactory.createWebPageBuilder(type);
        builder.buildSetWebPages("XMLData/webPages.xml");
        List<WebPage> pagesFromXml = new ArrayList<>(builder.getWebPages());
        pagesFromXml.sort(new WebPageComparator());

        return pagesFromXml.get(0);
    }
}
