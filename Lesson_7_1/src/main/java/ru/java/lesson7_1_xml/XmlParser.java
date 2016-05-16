package ru.java.lesson7_1_xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Abilis on 16.05.2016.
 */
public class XmlParser {

    private static final String FILE_NAME_FOR_PARSING = "D:\\temp\\testfile\\testdir\\files\\test.xml";


    public void init() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(FILE_NAME_FOR_PARSING));

        



    }
}
