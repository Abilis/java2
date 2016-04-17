package ru.java2.lesson_3_1;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Abilis on 17.04.2016.
 */
public class Main {

    public static void main(String[] args) {


        //Алгоритмы!

        //1 задание - найти максимальный элемент массива
        int[] testArr1 = new int[0];
        int[] testArr2 = new int[10];

        //заполняем тестовый массив случайными числами
        for (int i = 0; i < testArr2.length; i++) {
            testArr2[i] = (int) (Math.random() * testArr2.length + 1);
        }

        System.out.println(getMaxElement(testArr1)); //суем элемент нулевой длины

        System.out.println(Arrays.toString(testArr2)); //исходный тестовый массив
        System.out.println(getMaxElement(testArr2)); //максимальный элемент в тестовом массиве

        System.out.println();
        System.out.println();

        //2 задание
        //нужно написать метод разворота строки. Без стрингбилдера

        String testStr = "Очень сложное задание";
        String testStr2 = "";
        String testStr3 = null;
        System.out.println(testStr);
        System.out.println(reverseString(testStr));
        System.out.println(reverseString(testStr2));
        System.out.println(reverseString(testStr3));

        System.out.println();
        System.out.println();

        //3 задание
        //На вход подается строка. Нужно посчитать частоту вхождения каждой буквы в строке и вернуть
        //Map<String, Integer> - список букв и их частот
        String testStr4 = "А вот это уже поинтереснее!";
        String testStr5 = "";
        String testStr6 = null;
        String testStr7 ="\n";

        System.out.println(testStr4);
        Map<String, Integer> friquencyMap = getFriquencyMap(testStr4);

        for (Map.Entry<String, Integer> pair : friquencyMap.entrySet()) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }


    }


    private static int getMaxElement(int[] arr) {

        if (arr == null) {
            throw new IllegalArgumentException();
        }

        if (arr.length == 0) {
            return -1;
        }

        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }


        return max;
    }

    private static String reverseString(String str) {

        if (str == null) {
            return null;
        }

        String result = "";

        char[] strAsCharArr = str.toCharArray();

        for (int i = strAsCharArr.length - 1; i >= 0; i--) {
            result += strAsCharArr[i];
        }

        return result;
    }

    private static Map<String, Integer> getFriquencyMap(String str) {

        if (str == null) {
            return null;
        }

        Map<String, Integer> result = new TreeMap<String, Integer>();

        //преобразуем входную строку в массив символов
        char[] symbols = str.toCharArray();

        //бежим по массиву символов
        for (char sym : symbols) {

            //если в мапе еще нет текущего символа, то суем строковое представление символа с валуе = 1
            if (!result.containsKey(String.valueOf(sym))) {
                result.put(String.valueOf(sym), 1);
            }
            else { //если же есть - увеличиваем валуе на 1
                int count = result.get(String.valueOf(sym));
                result.put(String.valueOf(sym), ++count);
            }

        }


        return  result;
    }

}
