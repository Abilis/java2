package ru.java.lesson6_3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Abilis on 11.05.2016.
 */
public class Buffer {

    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(100);

    private Buffer() {

    }


    //возвращается false, если по таймауту не удалось добавить в очередь строку
    public static boolean addString(String str) throws InterruptedException {
        return blockingQueue.offer(str, 500, TimeUnit.MILLISECONDS);
    }

    //возвращается null, если по таймауту в очереди ничего не оказалось
    public static String getString() throws InterruptedException {
        String result = blockingQueue.poll(500, TimeUnit.MILLISECONDS);
        return result;
    }


}
