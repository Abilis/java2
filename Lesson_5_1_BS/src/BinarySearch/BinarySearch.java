package BinarySearch;

import java.util.Arrays;

/**
 * Created by Abilis on 01.05.2016.
 */
public class BinarySearch {

    private static int length = 10;

    public static void main(String[] args) {

        //cоздаем тестовый массив
        int[] testArr = getArr(length);

        System.out.println(Arrays.toString(testArr));
        Arrays.sort(testArr);
        System.out.println(Arrays.toString(testArr));

        int index = getIndex(testArr, 5);
        System.out.println(index);

    }

    private static int[] getArr(int length) {

        int[] result = new int[length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (Math.random() * result.length);
        }

        return result;
    }

    private static int getIndex(int[] arr, int value) {

        if (arr == null) {
            return -1;
        }

        //устанавливаем минимальный и максимальный индексы
        int minIndex = 0;
        int maxIndex = arr.length - 1;

        while (minIndex <= maxIndex) {

            int midIndex = (maxIndex + minIndex) / 2; //устанавливаем средний индекс

            if (value < arr[midIndex]) { //если значение меньше того, что в середине
                maxIndex = midIndex - 1; //смешаем максимальный на единицу меньше того, что было серединой
            }

            else if (value > arr[midIndex]) { //если же значение больше того, что в середине
                minIndex = midIndex + 1;      //смещаем минимальный на единицу больше того, что было в середине
            }

            else { //иначе - значение равно тому, что в середие. Мы нашли искомый элемент
                return midIndex;
            }

        }


        return -1;
    }
}
