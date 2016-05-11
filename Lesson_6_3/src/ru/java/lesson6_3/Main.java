package ru.java.lesson6_3;



import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Abilis on 11.05.2016.
 */
public class Main {

    private static final String DIR_NAME = "D:\\Temp\\testdir";
    private static final ConcurrentLinkedQueue<File> LIST_OF_FILES = new ConcurrentLinkedQueue<>();
    private static final String STRING_FOR_SEARCH = "7777";

    private volatile static Integer amountFindedString = 0; //число найденных строк

    public synchronized static void amountFindedStringIncrement() {
        amountFindedString++;
    }

    private volatile static int countFiles = 0;     //количество обработанных файлов
    private volatile static int countString = 0;    //количество обработанных строк

    public synchronized static void countFilesIncrement() {
        countFiles++;
    }

    public synchronized static void countStringIncrement() {
        countString++;
    }

    public static void main(String[] args) {

        File dir = new File(DIR_NAME);

        File[] files = dir.listFiles();


        LIST_OF_FILES.addAll(Arrays.asList(files));


        
        //создаем производителей
        Producer pr1 = new Producer(LIST_OF_FILES);
        Producer pr2 = new Producer(LIST_OF_FILES);
        Producer pr3 = new Producer(LIST_OF_FILES);
        Producer pr4 = new Producer(LIST_OF_FILES);

        //создаем потребителей
        Consumer con1 = new Consumer(LIST_OF_FILES, STRING_FOR_SEARCH);
        Consumer con2 = new Consumer(LIST_OF_FILES, STRING_FOR_SEARCH);
        Consumer con3 = new Consumer(LIST_OF_FILES, STRING_FOR_SEARCH);
        Consumer con4 = new Consumer(LIST_OF_FILES, STRING_FOR_SEARCH);

        long timeStart = System.currentTimeMillis();

        //стартуем производителей
        pr1.start();
        pr2.start();
        pr3.start();
        pr4.start();

        //стартуем потребителей
        con1.start();
        con2.start();
        con3.start();
        con4.start();


        while (true) {

            if (!pr1.isAlive() && !pr2.isAlive() && !pr3.isAlive() && !pr4.isAlive()
                    && !con1.isAlive() && !con2.isAlive() && !con3.isAlive() && !con4.isAlive()) {
                break;
            }


        }

        long timeFinish = System.currentTimeMillis();

        long delta = timeFinish - timeStart;

        System.out.println();
        System.out.println("Разбор файлов завершен. Затрачено времени: " + delta + " мс");
        System.out.println("Обработано файлов: " + countFiles + ", обработано строк: " + countString);
        System.out.println("Найдено подстрок \"" + STRING_FOR_SEARCH + "\": " + amountFindedString);


    }
}
