package WebPage;

public class MirrorWebPage extends WebPage {
    /**
     * isMailAvailable shows whether it`s possible to use mail
     */

    private boolean isMailAvailable;

    /**
     * isArchivesAvailable shows whether there`s any archive available
     */

    private boolean isArchivesAvailable;

    @Override
    public boolean isMailAvailable() {
        return isMailAvailable;
    }

    @Override
    public void setMailAvailable(boolean mailAvailable) {
        isMailAvailable = mailAvailable;
    }

    @Override
    public boolean isArchivesAvailable() {
        return isArchivesAvailable;
    }

    @Override
    public void setArchivesAvailable(boolean archivesAvailable) {
        isArchivesAvailable = archivesAvailable;
    }

    public MirrorWebPage() {
        super();
    }

    public MirrorWebPage(String title, boolean needsAuthorization, boolean isPollsAvailable,
                         boolean isPollNeedsAuthorization, boolean isPaid,
                         boolean isMailAvailable, boolean isArchivesAvailable) {
        super(title, needsAuthorization, isPollsAvailable, isPollNeedsAuthorization, isPaid);
        this.isMailAvailable = isMailAvailable;
        this.isArchivesAvailable = isArchivesAvailable;
    }


    @Override
    public String toString() {
        return "MirrorWebPage{\n" +
                "id=" + id + "\n" +
                "title='" + title + '\'' + "\n" +
                "isMailAvailable=" + isMailAvailable + "\n" +
                "isArchivesAvailable=" + isArchivesAvailable + "\n" +
                "needsAuthorization=" + needsAuthorization + "\n" +
                "arePollsAvailable=" + arePollsAvailable + "\n" +
                "arePollsNeedAuthorization=" + arePollsNeedAuthorization + "\n" +
                "isPaid=" + isPaid + "\n" +
                '}';
    }
}
