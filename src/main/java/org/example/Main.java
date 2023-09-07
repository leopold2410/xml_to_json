package org.example;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("test.xml")) {
            assert is != null;
            BufferedInputStream bis = new BufferedInputStream(is);
            String input = new String(bis.readAllBytes());
            System.out.printf("Input: %s\n", input);
            System.out.printf("Output: %s\n", XmlToJSON.transform(input));
        }


    }
}