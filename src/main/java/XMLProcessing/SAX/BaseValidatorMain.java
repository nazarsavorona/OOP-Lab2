package XMLProcessing.SAX;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Validator with main which determines whether xml valid or not (based on xsd schema).
 */
public class BaseValidatorMain {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Usage: <XML FILE PATH> <XSD FILE PATH>");
            System.exit(-1);
        }

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = args[0];
        String schemaName = args[1];

        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            File schemaLocation = new File(schemaName);
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new WebPageErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            System.err.printf("%s or/and %s is not correct or valid %n", fileName, schemaName);
        }
    }
}
