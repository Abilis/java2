package ru.java2.lesson_1_4;

import java.io.*;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Java Program to parse/read HTML documents from File using Jsoup library.
 * Jsoup is an open source library which allows Java developer to parse HTML
 * files and extract elements, manipulate data, change style using DOM, CSS and
 * JQuery like method.
 *
 * @author Javin Paul
 */
public class Main {

    public static void main(String args[]) throws IOException {

        Date date1 = new Date();

        Document habr;
        String title = "";
        String div = "";
        String url = "https://habrahabr.ru/";

        try {
            habr = Jsoup.connect(url).get();
            title = habr.title();
            div = habr.getElementsByTag("div").text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] divAsArr = div.split(" ");

        System.out.println(title);

        String fileName = "D:\\Temp\\file1.txt";

        try (
                FileWriter fileWriter = new FileWriter(fileName);
            )

            {
                StringBuilder sb = new StringBuilder();
                try {

                    for (int i = 0; i < divAsArr.length; i += 6) {
                        sb.append(divAsArr[i] + " " + divAsArr[i + 1] + " " + divAsArr[i + 2] + " " +
                                divAsArr[i + 3] + " " + divAsArr[i + 4] + " " + divAsArr[i + 5] + "\n");
                    }
                } catch (IndexOutOfBoundsException e) {
                    //если попали сюда - все нормально, просто закончился массив
                   //все равно нужно записать в файл
                }

                fileWriter.write(sb.toString());

        } catch (Exception e) {
            //конец записи в файл

        }


        Date date2 = new Date();

        long difference = date2.getTime() - date1.getTime();

        System.out.println("Затраты времени: " + difference / 1000 + " секунд");

    }
}