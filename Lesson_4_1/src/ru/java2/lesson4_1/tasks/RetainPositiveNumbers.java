package ru.java2.lesson4_1.tasks;


import java.util.*;

/**
 * Created by Abilis on 27.04.2016.
 */
public class RetainPositiveNumbers {

    public static int[] retainPositiveNumbers(int[] a) {
    /*
    Please implement this method to
    return a new array with only positive numbers from the given array.
    The elements in the resulting array shall be sorted in the ascending order.
    */

       List positiveNumbers = new LinkedList<>();

        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                positiveNumbers.add(a[i]);
            }
        }

        int[] result = new int[positiveNumbers.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = (int) positiveNumbers.get(i);
        }

        Arrays.sort(result);

        return result;
    }
}

