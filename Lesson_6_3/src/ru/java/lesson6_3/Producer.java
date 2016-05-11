package ru.java.lesson6_3;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Abilis on 11.05.2016.
 */
public class Producer extends Thread {

    private static ConcurrentLinkedQueue<File> listOfFiles;
    private static int countFiles = 0;
    private static int countString = 0;

    public Producer(ConcurrentLinkedQueue<File> listOfFiles) {
        this.listOfFiles = listOfFiles;
    }

    @Override
    public void run() {

        while (!isInterrupted()) {
            File currentFile = getFile();   //берем из очереди файлов очередной файл
            if (currentFile == null) {      //если из очереди вернулся нулл - файлы кончились
                break;
            }

            System.out.println("Начата обработка файла " + ++countFiles);
            Main.countFilesIncrement();

            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(currentFile)); //Открываем новый поток на чтение из файла
                String line = null;

                while ((line = bufferedReader.readLine()) != null) { //читаем построчно файл

                    if (Buffer.addString(line)) {                    //суем прочитанную строку в очередь-буфер
                        countString++;
                        Main.countStringIncrement();
                    }
                    else {
                        System.out.println("Работа производителя прервана по таймауту!");
                        interrupt();
                        break;
                    }

                }



            } catch (IOException e1) {
                System.out.println("IOException: " + e1.getMessage());

            } catch (InterruptedException e2) {
                System.out.println("Работа производителя " + Thread.currentThread().getName() + " прервана!");
            }

            Util.closeQuitely(bufferedReader);

        }

//        System.out.println("Работа производителя " + Thread.currentThread().getName() + " завершена!" +
//                " На текущий момент обработано строк: " + countString + ", обработано файлов: " + countFiles);

            System.out.println("Работа производителя " + Thread.currentThread().getName() + " завершена!");

    }

    //возвращает null, если очередь с файлами пуста
    private File getFile() {
        return listOfFiles.poll();
    }
}
