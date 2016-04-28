package ru.java2.lesson4_1;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abilis on 26.04.2016.
 */
public class MyPriorityQueue extends BinaryHeap implements PriorityQueue {

    private BinaryHeap binaryHeap;
    private ArrayList<Integer> keys = new ArrayList<Integer>();

    public MyPriorityQueue(List<Integer> list) {
        super(list);
    }

    //извлечение с удалением элемента с максимальный приоритетом
    @Override
    public int getMax() {


        return 0;
    }

    //Добавление нового элемента по ключу
    @Override
    public void insert(Comparable comparable, Object o) {


    }
}
