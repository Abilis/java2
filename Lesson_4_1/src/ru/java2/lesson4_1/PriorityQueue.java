package ru.java2.lesson4_1;

/**
 * Created by Abilis on 25.04.2016.
 */
interface PriorityQueue<K extends Comparable<K>, T> {

    int getMax();

    void insert(K k, T t);
}