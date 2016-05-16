package ru.java.lesson7_1_xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Abilis on 16.05.2016.
 */
public class Main {

    public static void main(String[] args) {
        XmlParser parser = new XmlParser();

        try {
            parser.init();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Скорее всего, неверно указан файл");
            e.printStackTrace();
        }
    }

}
