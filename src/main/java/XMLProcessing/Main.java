package XMLProcessing;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: <XML FILE PATH> <PARSER TYPE>");
            System.exit(-1);
        }

        AbstractWebPagesBuilder builder = WebPageBuilderFactory.createWebPageBuilder(args[1]);
        builder.buildSetWebPages(args[0]);
        System.out.println(builder.getWebPages());
    }
}
