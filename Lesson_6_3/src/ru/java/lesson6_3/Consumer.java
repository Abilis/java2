package ru.java.lesson6_3;

import java.io.File;
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

        while (!isInterrupted()) {

            String str = null;
            try {
                str = Buffer.getString();   //берем из очереди строк очередную строку
                if (str == null) {          //если вернулся нулл - в очереди ничего не было по таймауту
                    System.out.println("Работа потребителя " + Thread.currentThread().getName() + " прервана по таймауту!");
                    interrupt();
                    break;
                }

                processingString(str);

            } catch (InterruptedException e) {
                System.out.println("Работа потребителя " + Thread.currentThread().getName() + " прервана!");
            }


        }

//        System.out.println("Работа потребителя " + Thread.currentThread().getName() + " завершена. На текущий момент" +
//                " найдено подстрок \"" +
//        stringForSearch + "\": " + amountFindedString);

        System.out.println("Работа потребителя " + Thread.currentThread().getName() + " завершена");
    }


    private static void processingString(String str) {

        if (str.contains(stringForSearch)) {
            System.out.println("Найдена искомая подстрока \"" + stringForSearch + "\"!");
            System.out.println(str);
            System.out.println();
            synchronized (amountFindedString) {
                amountFindedString++;
                Main.amountFindedStringIncrement();
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
