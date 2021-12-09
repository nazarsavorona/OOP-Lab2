package WebPage;

/**
 * Class represents advertisement page type
 * Has no special fields
 */

public class AdvertisementWebPage extends WebPage {
    public AdvertisementWebPage() {
        super();
    }

    protected AdvertisementWebPage(String title, boolean needsAuthorization,
                                   boolean isPollsAvailable, boolean isPollNeedsAuthorization,
                                   boolean isPaid) {
        super(title, needsAuthorization, isPollsAvailable, isPollNeedsAuthorization, isPaid);
    }

    @Override
    public String toString() {
        return "AdvertisementWebPage{\n" +
                "id=" + id + "\n" +
                "title='" + title + '\'' + "\n" +
                "needsAuthorization=" + needsAuthorization + "\n" +
                "arePollsAvailable=" + arePollsAvailable + "\n" +
                "arePollsNeedAuthorization=" + arePollsNeedAuthorization + "\n" +
                "isPaid=" + isPaid + "\n" +
                '}';
    }
}
