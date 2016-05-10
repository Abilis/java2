package ru.java2.lesson6_1_executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Main {

    public static void main(String[] args) {

        Thread t1 = new TestThread();
        t1.setName("Первый поток");

        Thread t2 = new TestThread();
        t2.setName("Второй поток");

      /*  t1.start();
        t2.start();*/


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(t1);
        executorService.submit(t2);


        System.out.println(Thread.currentThread().getName());


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        t1.interrupt();
        t2.interrupt();

    }

}
