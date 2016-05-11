package ru.java2.lesson6_2;


import java.io.*;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Producer extends Thread {

    private static BufferedReader bufferedReader;
    private static String fileName;
    private static int amountString = 0;


    public Producer(String fileName) {
        this.fileName = fileName;
        initFileReader();
    }


    @Override
    public void run() {

        try {

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (Buffer.addString(line)) {
                    amountString++;
                }
                else {
                    interrupt();
                }

            }

        } catch (IOException e1) {
            System.out.println("Не удалось считать очередную строку из потока чтения файла");
        } catch (InterruptedException e2) {
            System.out.println("Работа производителя прервана");
        }

        closeQutely(bufferedReader);
        System.out.println("Производитель закончил разбор файла. Разобрано строк: " + amountString);


    }

    private static void initFileReader() {

        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось открыть поток чтения из файла " + fileName);
            closeQutely(bufferedReader);
        }


    }

    public static void closeQutely(Closeable resource) {

        if (resource != null) {
            try {
                resource.close();
            } catch (IOException ignore) {
            /*NOP*/
            }
        }
    }

}
