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

            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(currentFile)); //Открываем новый поток на чтение из файла
                String line = null;

                while ((line = bufferedReader.readLine()) != null) { //читаем построчно файл

                    if (Buffer.addString(line)) {                    //суем прочитанную строку в очередь-буфер
                        countString++;
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

        System.out.println("Работа производителя " + Thread.currentThread().getName() + " завершена!" +
                " Обработано строк: " + countString + ", обработано файлов: " + countFiles);


    }

    private File getFile() {
        if (!listOfFiles.isEmpty()) {
            return listOfFiles.poll();

        }
        else {
            interrupted();
            return null;
        }
    }
}
