package ru.java2.lesson4_1;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Abilis on 25.04.2016.
 */
public class Main {

    public static void main(String[] args) {

        Queue<Integer> q2 = new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {


                return 0;
            }
        });

        for (int i = 5; i >= 1; i--) {
            q2.add(i);
        }

        while (!q2.isEmpty()) {
            System.out.println(q2.poll());
        }

    }

}
