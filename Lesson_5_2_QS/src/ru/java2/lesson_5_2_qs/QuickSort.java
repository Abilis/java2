package ru.java2.lesson_5_2_qs;

import java.util.Arrays;

/**
 * Created by Abilis on 01.05.2016.
 */
public class QuickSort {

    private static int lenght = 10;

    public static void main(String[] args) {

        int[] testArr = getArr(lenght);

        System.out.println(Arrays.toString(testArr));
        testArr = qSort(testArr);
        System.out.println(Arrays.toString(testArr));
    }

    public static int[] getArr(int lenght) {

        int[] result = new int[lenght];

        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (Math.random() * result.length);
        }

        return result;
    }

    public static int[] qSort(int[] arr) {

        if (arr == null) {
            return null;
        }

        arr = qSortOrig(arr, 0, arr.length - 1);

        return arr;
    }

    private static int[] qSortOrig(int[] arr, int start, int end) {

        //условие выхода из рекурсивных вызовов
        if (start >= end) {
            return arr;
        }


        //устанавливаем индексы. В качестве опорного будет середина
        int min = start;
        int max = end;
        int middle = min + (max - min) / 2;

        while (min < max) {

            //крутим цикл пока значение слева от опорного меньше либо равно опорному. Либо пока не дошли до опорного
            while (min < middle && arr[min] <= arr[middle]) {
                min++;
            }

            //пока значение справа от опорного больше либо равно опорному. Либо пока не дошли до опорного
            while (max > middle && arr[max] >= arr[middle]) {
                max--;
            }

            //если элемент слева больше элемента справа от опорного, то их нужно поменять местами
            if (arr[min] > arr[max]) {
                int temp = arr[min];
                arr[min] = arr[max];
                arr[max] = temp;

                //если один из индексов дошел до опорного, то сдвигаем опорный до второго индекса
                if (min == middle) {
                    middle = max;
                }
                else if (max == middle) {
                    middle = min;
                }
            }



        }

        qSortOrig(arr, start, middle - 1);
        qSortOrig(arr, middle + 1, end);

        return arr;
    }
}
