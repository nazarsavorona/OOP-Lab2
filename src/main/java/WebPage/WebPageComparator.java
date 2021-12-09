package WebPage;

import java.util.Comparator;

public class WebPageComparator implements Comparator<WebPage> {
    @Override
    public int compare(WebPage o1, WebPage o2) {
        return o1.compareTo(o2);
    }
}
