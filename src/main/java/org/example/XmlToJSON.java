package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.burt.jmespath.Expression;
import io.burt.jmespath.JmesPath;
import io.burt.jmespath.jackson.JacksonRuntime;

import java.io.IOException;

public class XmlToJSON {
    public static String transform(String input) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(input.getBytes());
        return jsonNode.toPrettyString();
    }

    public static String transformAndMap(String input, String jmesPathMappingTemplate) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(input.getBytes());

        JmesPath<JsonNode> jmespath = new JacksonRuntime();
// Expressions need to be compiled before you can search. Compiled expressions
// are reusable and thread safe. Compile your expressions once, just like database
// prepared statements.
        Expression<JsonNode> expression = jmespath.compile(jmesPathMappingTemplate);

// Finally this is how you search a structure. There's really not much more to it.
        JsonNode result = expression.search(jsonNode);
        return result.toPrettyString();
    }
}
