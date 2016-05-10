package ru.java2.lesson6_2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Buffer {

    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);


    private Buffer() {

    }


    public static void addString(String string) throws InterruptedException {
        blockingQueue.offer(string, 500, TimeUnit.MILLISECONDS);
//        System.out.println("Добавлена строка. Общий размер очереди: " + blockingQueue.size());
//        Thread.sleep(5500);
    }


    public static String getString() throws InterruptedException {
        String result = blockingQueue.poll(500, TimeUnit.MILLISECONDS);
//        System.out.println("Взята строка из очереди. Общий размер очереди: " + blockingQueue.size());
//        Thread.sleep(2500);
        return result;
    }

}
