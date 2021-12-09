package WebPage;

public class NewsWebPage extends WebPage {
    private boolean isMailAvailable;
    private boolean isNewsAvailable;

    public NewsWebPage() {
        super();
    }

    public boolean isMailAvailable() {
        return isMailAvailable;
    }

    public void setMailAvailable(boolean mailAvailable) {
        isMailAvailable = mailAvailable;
    }

    public boolean isNewsAvailable() {
        return isNewsAvailable;
    }

    public void setNewsAvailable(boolean newsAvailable) {
        isNewsAvailable = newsAvailable;
    }

    @Override
    public String toString() {
        return "NewsWebPage{\n" +
                "id=" + id + "\n" +
                "title='" + title + '\'' + "\n" +
                "isMailAvailable=" + isMailAvailable + "\n" +
                "isNewsAvailable=" + isNewsAvailable + "\n" +
                "needsAuthorization=" + needsAuthorization + "\n" +
                "arePollsAvailable=" + arePollsAvailable + "\n" +
                "arePollsNeedAuthorization=" + arePollsNeedAuthorization + "\n" +
                "isPaid=" + isPaid + "\n" +
                '}';
    }

    public NewsWebPage(String title, boolean needsAuthorization, boolean isPollsAvailable,
                       boolean isPollNeedsAuthorization, boolean isPaid, boolean isMailAvailable,
                       boolean isNewsAvailable) {
        super(title, needsAuthorization, isPollsAvailable, isPollNeedsAuthorization, isPaid);
        this.isMailAvailable = isMailAvailable;
        this.isNewsAvailable = isNewsAvailable;
    }
}
