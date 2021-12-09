package WebPage;

public enum WebPageType {
    MIRROR("Mirror"),
    NEWS("News"),
    PORTAL("Portal"),
    ADVERTISEMENT("Advertisement");

    private String value;

    WebPageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
