package ru.java2.lesson4_1;


import java.util.*;

/**
 * Created by Abilis on 25.04.2016.
 */
public class Main {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3);

        List<Integer> res = map(numbers, new
                SquareOperator());
    }

    interface Operator<R, T> {
        R apply(T t);
    }

    private static class SquareOperator implements Operator {

        @Override
        public Object apply(Object o) {
            return null;
        }

        public void map() {
            
        }
    }

}
