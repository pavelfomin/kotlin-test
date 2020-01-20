package net.test;

import org.junit.jupiter.api.Test;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;

class XmlParserTest {

    @Test
    void parse() throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        InputStream input = getClass().getClassLoader().getResourceAsStream("xml/test.xml");
        parser.parse(input, new DefaultHandler());
    }

    @Test
    void validate() throws Exception {

        InputStream input = getClass().getClassLoader().getResourceAsStream("xml/test.xml");

        InputStream schemaStream = getClass().getClassLoader().getResourceAsStream("xml/test.xsd");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new StreamSource(schemaStream));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(input));
    }
}