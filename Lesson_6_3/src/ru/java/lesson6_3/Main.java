package ru.java.lesson6_3;



import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Created by Abilis on 11.05.2016.
 */
public class Main {

    private static final String DIR_NAME = "D:\\temp\\testfile\\testdir";
    private static final ConcurrentLinkedQueue<File> LIST_OF_FILES = new ConcurrentLinkedQueue<>();
    private static final String STRING_FOR_SEARCH = "9999";


    public static void main(String[] args) {

        File dir = new File(DIR_NAME);

        File[] files = dir.listFiles();


        LIST_OF_FILES.addAll(Arrays.asList(files));


        //производители
        Producer producer = new Producer(LIST_OF_FILES);

        //потребители
        Consumer consumer = new Consumer(LIST_OF_FILES, STRING_FOR_SEARCH);

        long timeStart = System.currentTimeMillis();

        producer.start();
        consumer.start();

        while (producer.isAlive() || consumer.isAlive()) {

        }

        long timeFinish = System.currentTimeMillis();

        long delta = timeFinish - timeStart;

        System.out.println();
        System.out.println("Разбор файлов завершен. Затрачено времени: " + delta + " мс");


    }
}
