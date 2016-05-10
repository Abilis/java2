package ru.java2.lesson6_2;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Producer extends Thread {

    private BufferedReader bufferedReader;
    private int countLines = 0;

    public Producer(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }


    @Override
    public void run() {

            try {

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    Buffer.addString(line);
                    countLines++;
                }
            } catch (IOException e) {
                System.out.println("Какое-то IOException " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Производитель закончил работу из-за пустой очереди");
            }



        Util.cloceQutely(bufferedReader);

        System.out.println("Разбор файла производителем завершен! Обработано строк: " + countLines);

    }

}
