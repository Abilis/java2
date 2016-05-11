package ru.java.lesson6_3;

/**
 * Created by Abilis on 11.05.2016.
 */
public class BufferObserver extends Thread {

    private int sizeQueue = 0;
    private double averageSizeQueue = 0.0;

    @Override
    public void run() {

        while (!isInterrupted()) {
            sizeQueue = Buffer.getSizeQueue();
            System.out.println("Заполненность буфера: " + sizeQueue);

            averageSizeQueue = (averageSizeQueue + sizeQueue) / 2;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println();
        System.out.println("Работа наблюдателя за буфером-очередью завершена! Средний размер очереди: " + averageSizeQueue);

    }
}
