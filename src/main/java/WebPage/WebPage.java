package WebPage;

public abstract class WebPage implements Comparable<WebPage> {
    protected int id;
    protected String title;
    protected boolean needsAuthorization;
    protected boolean arePollsAvailable = false;
    protected boolean arePollsNeedAuthorization = false;
    protected boolean isPaid;

    protected WebPage() {
        this.id = -1;
    }

    protected WebPage(String title, boolean needsAuthorization, boolean arePollsAvailable,
                      boolean arePollsNeedAuthorization, boolean isPaid) {
        this.id = -1;
        this.title = title;
        this.needsAuthorization = needsAuthorization;
        this.arePollsAvailable = arePollsAvailable;
        this.arePollsNeedAuthorization = arePollsNeedAuthorization;
        this.isPaid = isPaid;

        if (!arePollsAvailable) {
            this.arePollsNeedAuthorization = false;
        }
    }

    @Override
    public String toString() {
        return "WebPage{\n" +
                "id=" + id + "\n" +
                "title='" + title + '\'' + "\n" +
                "needsAuthorization=" + needsAuthorization + "\n" +
                "arePollsAvailable=" + arePollsAvailable + "\n" +
                "arePollsNeedAuthorization=" + arePollsNeedAuthorization + "\n" +
                "isPaid=" + isPaid + "\n" +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isNeedsAuthorization() {
        return needsAuthorization;
    }

    public void setNeedsAuthorization(boolean needsAuthorization) {
        this.needsAuthorization = needsAuthorization;
    }

    public boolean isArePollsAvailable() {
        return arePollsAvailable;
    }

    public void setArePollsAvailable(boolean arePollsAvailable) {
        this.arePollsAvailable = arePollsAvailable;
    }

    public boolean isArePollsNeedAuthorization() {
        return arePollsNeedAuthorization;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArePollsNeedAuthorization(boolean arePollsNeedAuthorization) {
        this.arePollsNeedAuthorization = arePollsNeedAuthorization;

        if (arePollsAvailable == false) {
            this.arePollsNeedAuthorization = false;
            this.arePollsNeedAuthorization= false;
        }
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isMailAvailable() {
        throw new RuntimeException("Abstract class method was called");
    }

    public void setMailAvailable(boolean mailAvailable) {
        throw new RuntimeException("Abstract class method was called");
    }

    public boolean isArchivesAvailable() {
        throw new RuntimeException("Abstract class method was called");
    }

    public void setArchivesAvailable(boolean archivesAvailable) {
        throw new RuntimeException("Abstract class method was called");
    }

    public boolean isNewsAvailable() {
        throw new RuntimeException("Abstract class method was called");
    }

    public void setNewsAvailable(boolean newsAvailable) {
        throw new RuntimeException("Abstract class method was called");
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(WebPage o) {
        Integer obj1 = id;
        Integer obj2 = o.id;

        return obj1.compareTo(obj2);
    }
}
