package XMLProcessing;

import XMLProcessing.DOM.WebPagesDomBuilder;
import XMLProcessing.SAX.WebPagesSaxBuilder;
import XMLProcessing.StAX.WebPagesStaxBuilder;

public class WebPageBuilderFactory {
    /**
     * Enum containing possible types of parser.
     */
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private WebPageBuilderFactory() {
    }

    /**
     * Method creating actual builder determined by type.
     * @param typeParser String represents type of parser to use.
     * @return AbstractFlowersBuilder, abstract class.
     */
    public static AbstractWebPagesBuilder createWebPageBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM -> {
                return new WebPagesDomBuilder();
            }
            case STAX -> {
                return new WebPagesStaxBuilder();
            }
            case SAX -> {
                return new WebPagesSaxBuilder();
            }
            default -> throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }
}
