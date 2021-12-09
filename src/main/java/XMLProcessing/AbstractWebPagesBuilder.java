package XMLProcessing;

import WebPage.WebPage;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class which uses Factory pattern to build set of data from Xml.
 */
public abstract class AbstractWebPagesBuilder {
    /**
     * Set of webPages.
     */
    protected Set<WebPage> webPages;

    public AbstractWebPagesBuilder() {
        webPages = new HashSet<>();
    }

    public AbstractWebPagesBuilder(Set<WebPage> webPages) {
        this.webPages = webPages;
    }

    public Set<WebPage> getWebPages() {
        return webPages;
    }

    public abstract void buildSetWebPages(String filename);
}

