package ru.java2.lesson6_1_executors;

/**
 * Created by Abilis on 10.05.2016.
 */
public class TestThread extends Thread {

    private int count;



    @Override
    public void run() {

        while (!isInterrupted()) {
            System.out.println(getName() + ": " + count++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(getName() + " прервано!");
    }
}
