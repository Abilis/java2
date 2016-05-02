package ru.java2.lesson_5_3_CS;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Abilis on 02.05.2016.
 */
public class CountingSort {

    private static int lenght = 10;

    public static void main(String[] args) {

        int[] testArr = getArr(lenght);
        System.out.println("Исходный массив:\n" + Arrays.toString(testArr) + "\n");

        int[] sortedArr = countSort(testArr);
        System.out.println("Отсортированный массив:\n" + Arrays.toString(sortedArr));

    }

    public static int[] countSort(int[] arr) {

        //значение элемента, количество повторов
        Map<Integer, Integer> counters = new TreeMap<Integer, Integer>();


        for (int i = 0; i < arr.length; i++) {

            int oldValueAmount = 0;                     //дефолтное значение количества_повторов
            if (counters.containsKey(arr[i])) {         //если counters содержит элемент массива в виде ключа
                oldValueAmount = counters.get(arr[i]);  //то дефолтное значение изменяется на число элементов
            }
            counters.put(arr[i], ++oldValueAmount);     //записываем в карту новую пару число - количество_повторов

        }

        int currentIndexOfArr = 0;

        for (Map.Entry<Integer, Integer> pair : counters.entrySet()) { //проходим по сформированной карте

            int value = pair.getKey();               //на каждой итерации вытаскиваем пару
            int amountValue = pair.getValue();       //значение - количество повторов

            for (int i = 0; i < amountValue; i++) {  //теперь прописываем в исходный массив текущий элемент
                arr[currentIndexOfArr] = value;      //столько раз, сколько есть повторов
                currentIndexOfArr++;                 //и каждый раз увеличиваем номер индекса
            }

        }


        return arr;
    }

    public static int[] getArr(int lenght) {

        int[] result = new int[lenght];

        for (int i = 0; i < lenght; i++) {
            result[i] = (int) (Math.random() * lenght / 2);
        }
        return result;
    }
}
