package ru.java2.lesson6_2;

import java.io.*;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Main {

    private static final String FILE_NAME = "D:\\Temp\\1984.TXT";
    private static final String FILE_NAME2 = "D:\\Temp\\file1.txt";
    private static final String STRING_FOR_SEARCH = "Уинстон";


    public static void main(String[] args) throws FileNotFoundException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME2));


        Producer producer = new Producer(bufferedReader);
        Consumer consumer = new Consumer(STRING_FOR_SEARCH);

        long start = System.currentTimeMillis();

        producer.start();
        consumer.start();

        while (producer.isAlive() || consumer.isAlive()) {
            /*NOP*/
        }


        long finish = System.currentTimeMillis();

        long delta = finish - start;
        System.out.println("Затрачено времени: " + delta + " мс");



    }

}
