package ru.java2.lesson6_2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Buffer {

    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);


    private Buffer() {

    }


    public static boolean addString(String string) throws InterruptedException {
        return blockingQueue.offer(string, 500, TimeUnit.MILLISECONDS);
    }


    public static String getString() throws InterruptedException {
        String result = blockingQueue.poll(500, TimeUnit.MILLISECONDS);
        return result;
    }

}
