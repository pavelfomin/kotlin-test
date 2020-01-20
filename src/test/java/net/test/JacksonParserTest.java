package net.test;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import net.test.xml.Office;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JacksonParserTest {

    XmlMapper xmlMapper = new XmlMapper();

    @Test
    void serialize() throws Exception {

    }

    @Test
    void deserialize() throws Exception {

        xmlMapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.getFactory().getXMLOutputFactory().setProperty(com.ctc.wstx.api.WstxOutputProperties.P_USE_DOUBLE_QUOTES_IN_XML_DECL, true);

        InputStream stream = getClass().getClassLoader().getResourceAsStream("xml/test.xml");
        URL resource = getClass().getClassLoader().getResource("xml/test.xml");
        assertNotNull(resource);

        Office document = xmlMapper.readValue(resource, Office.class);
        assertNotNull(document);
        assertNotNull(document.getUpdated());

        StringWriter writer = new StringWriter();
        xmlMapper.writeValue(writer, document);

        String output = writer.toString();
        System.out.println(output);
        assertEquals(readStreamContent(stream).replaceAll("\\s", ""), output.replaceAll("\\s", ""));
    }

    private String readStreamContent(InputStream stream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }
}