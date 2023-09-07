package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class XmlToJSONTest {
    static final Logger log = Logger.getLogger(XmlToJSONTest.class.getName());
    static String loadContent(String filename) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream(filename)) {
            assert is != null;
            BufferedInputStream bis = new BufferedInputStream(is);
            return new String(bis.readAllBytes());
        }
    }
    @Test
    void simpleXMLTransformation() throws IOException {

        String input = loadContent("test.xml");
        log.info("Input: \n" + input);
        String expectedOutput = loadContent("expected_test.json");
        String output = XmlToJSON.transform(input);
        log.info("Output: \n" + output);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void xmlWithAttributesTransformation() throws IOException {

        String input = loadContent("test_with_attributes.xml");
        log.info("Input: \n" + input);
        String expectedOutput = loadContent("expected_test_with_attributes.json");
        String output = XmlToJSON.transform(input);
        log.info("Output: \n" + output);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void simpleXMLTransformAndMap() throws IOException {
        String jmesPathExpression = "location[?state == 'WA'].name | sort(@) | {WashingtonCities: join(', ', @)}";
        String input = loadContent("test_cities.xml");
        log.info("Input: \n" + input);
        String expectedOutput = loadContent("expected_test_cities.json");
        String mappedOutput = XmlToJSON.transformAndMap(input, jmesPathExpression);
        log.info("Mapped Output: \n" + mappedOutput);
        Assertions.assertEquals(expectedOutput, mappedOutput);
    }
}