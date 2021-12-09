package WebPage;

public enum WebPageXmlTag {
    SITE("site"),
    ID("id"),
    PAGE("page"),
    TYPE("type"),
    TITLE("title"),
    CHARS("chars"),
    MAIL("mail"),
    NEWS("news"),
    PAID("paid"),
    POLL("poll"),
    ARCHIVE("archive"),
    AUTHORIZATION("authorisation");

    private String value;

    WebPageXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
