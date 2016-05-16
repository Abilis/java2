package ru.java.lesson7_1_xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Abilis on 16.05.2016.
 */
public class XmlParser {

    private static final String FILE_NAME_FOR_PARSING = "test2.xml";


    public void init() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(FILE_NAME_FOR_PARSING));

        visit(doc, 0);
    }

    private void visit(Node node, int level) {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node childNode = list.item(i); // текущий нод
            process(childNode, level); // обработка
            visit(childNode, level + 1); // рекурсия
        }
    }

    private void process(Node node, int level) {

        if (node.getNodeName().equals("#text")) {   //избавление от шляпной шляпы
            return;
        }

        for (int i = 0; i < level; i++) {
            System.out.print('\t');
        }

        System.out.print("node: " + node.getNodeName() + " ");


        if (node instanceof Element){
            Element e = (Element) node;

            if (!e.getAttribute("id").equals("")) {
                System.out.print("[id=" + e.getAttribute("id") + "]");
            }
            else if (!e.getAttribute("name").equals("")) {
                System.out.print("[name=\"" + e.getAttribute("name") + "\"]");
            }
            else if (!e.getAttribute("data").equals("")) {
                System.out.print("[data=\"" + e.getAttribute("data") + "\"]");
            }
        }

        System.out.println();



    }
}


