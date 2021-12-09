package WebPage;

public class WebPageFactory {
    public static WebPage getInstance(WebPageType type) {
        return switch (type) {
            case MIRROR -> new MirrorWebPage();
            case NEWS -> new NewsWebPage();
            case PORTAL -> new PortalWebPage();
            case ADVERTISEMENT -> new AdvertisementWebPage();
        };
    }

    private WebPageFactory() {
    }
}
