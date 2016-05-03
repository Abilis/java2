package ru.java2.lesson5_6_MS;

import java.util.Arrays;

/**
 * Created by Abilis on 03.05.2016.
 */
public class MergeSort {

    public static int length = 10;

    public static void main(String[] args) {

        int[] testArr = getArr(length);

        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(testArr));
        int[] sortedArr = mSort(testArr);
        System.out.println("Отсортированный массив:");
        System.out.println(Arrays.toString(sortedArr));

    }

    public static int[] mSort(int[] arr) {

        if (arr == null) {
            return null;
        }

        //массив длиной 0 и 1 уже отсортированы
        if (arr.length <= 1) {
            return arr;
        }

        //делим массив на две половины
        int[] leftSubArr = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightSubArr = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

        //сортируем каждую половину независимо друг от друга
        int[] resLeft = mSort(leftSubArr);
        int[] resRight = mSort(rightSubArr);

        //сливаем результаты
        int[] result = megreArrs(resLeft, resRight);

        return  result;
    }


    //метод сливает два отсортированных массива. В результате получает отсортированный массив
    private static int[] megreArrs(int[] arr1, int[] arr2) {

        int[] result = new int[arr1.length + arr2.length];

        int currentIndexArr1 = 0;
        int currentIndexArr2 = 0;

        for (int i = 0; i < result.length; i++) {

            if (currentIndexArr1 < arr1.length && currentIndexArr2 < arr2.length) {

                if (arr1[currentIndexArr1] < arr2[currentIndexArr2]) {
                    result[i] = arr1[currentIndexArr1];
                    currentIndexArr1++;
                }
                else {
                    result[i] = arr2[currentIndexArr2];
                    currentIndexArr2++;
                }
            }
            //первый массив не закончился, второй - закончился
            else if (currentIndexArr1 < arr1.length) {
                result[i] = arr1[currentIndexArr1];
                currentIndexArr1++;
            }
            //первый массив закончился, второй - не закончился
            else {
                result[i] = arr2[currentIndexArr2];
                currentIndexArr2++;
            }


        }


        return result;
    }


    public static int[] getArr(int length) {

        int[] result = new int[length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (Math.random() * result.length);
        }

        return result;
    }
}
