package WebPage;

public class PortalWebPage extends WebPage {
    private boolean isMailAvailable;

    public PortalWebPage() {
        super();
    }

    public PortalWebPage(String title, boolean needsAuthorization, boolean isPollsAvailable,
                         boolean isPollNeedsAuthorization, boolean isPaid, boolean isMailAvailable) {
        super(title, needsAuthorization, isPollsAvailable, isPollNeedsAuthorization, isPaid);
        this.isMailAvailable = isMailAvailable;
    }

    public boolean isMailAvailable() {
        return isMailAvailable;
    }

    public void setMailAvailable(boolean mailAvailable) {
        isMailAvailable = mailAvailable;
    }

    @Override
    public String toString() {
        return "PortalWebPage{\n" +
                "id=" + id + "\n" +
                "title='" + title + '\'' + "\n" +
                "isMailAvailable=" + isMailAvailable + "\n" +
                "needsAuthorization=" + needsAuthorization + "\n" +
                "arePollsAvailable=" + arePollsAvailable + "\n" +
                "arePollsNeedAuthorization=" + arePollsNeedAuthorization + "\n" +
                "isPaid=" + isPaid + "\n" +
                '}';
    }
}
