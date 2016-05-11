package ru.java.lesson6_3;

import java.io.File;
import java.text.BreakIterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Abilis on 11.05.2016.
 */
public class Consumer extends Thread {

    private static ConcurrentLinkedQueue<File> listOfFiles;
    private static String stringForSearch;
    private static Integer amountFindedString = 0;

    public Consumer(ConcurrentLinkedQueue<File> listOfFiles, String stringForSearch) {
        this.listOfFiles = listOfFiles;
        this.stringForSearch = stringForSearch;
    }

    @Override
    public void run() {



    }


    private static void processingString(String str) {

        if (str.contains(stringForSearch)) {
//            System.out.println("Найдена искомая подстрока!");
//            System.out.println(str);
//            System.out.println();
            synchronized (amountFindedString) {
                amountFindedString++;
            }
        }

    }

    private File getFile() {
        if (!listOfFiles.isEmpty()) {
            return listOfFiles.peek();

        }
        else {
            interrupted();
            return null;
        }
    }

}
