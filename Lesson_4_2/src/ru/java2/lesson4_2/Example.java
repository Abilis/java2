package ru.java2.lesson4_2;

import java.util.ArrayList;

/**
 * Created by Abilis on 01.05.2016.
 */
public class Example {

    private byte[] ballast;
    private static int count = 0;

    public Example() {
        ballast = new byte[10 * 1024 * 1024];
    }

    public static void main(String[] args) {

        while (true) {
            new Example().new Test().addInList();
            System.out.println(++count);
        }
    }



    class Test {
        private void addInList() {
            ForList.listOfTest.add(this);
        }
    }
}


class ForList {
    static ArrayList<Example.Test> listOfTest = new ArrayList<>();
}
