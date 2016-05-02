package ru.java2.lesson5_4_IS;

import java.util.Arrays;

/**
 * Created by Abilis on 02.05.2016.
 */
public class InsertionSort {

    private static int length = 10;

    public static void main(String[] args) {

        int[] testArr = getArr(length);
        System.out.println("Исходный массив:\n" + Arrays.toString(testArr) + "\n");

        int[] sortedArr = insertionSort(testArr);
        System.out.println("Отсортированный массив:\n" + Arrays.toString(sortedArr));

    }

    public static int[] insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) { //проходим по всему массиву начиная со второго по счету элемента

            //пока следующий элемент больше предыдущего и не дошли до начала
            while (i > 0 && arr[i] < arr[i - 1]) { //

                int temp = arr[i];  //меняем элементы местами
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
                i--;                //при каждой замене уменьшаем индекс в массиве на единицу
            }
        }

        return arr;
    }


    public static int[] getArr(int length) {

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = (int) (Math.random() * length);
        }
        return result;
    }
}
