package ru.java2.lesson4_1.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abilis on 27.04.2016.
 */
public class PerfectNumbers {

    public static List<Integer> getPerfectNumbers(int from, int to) {
    /*
      Please implement this method to
      return a list of all perect numbers in the given range inclusively.
      A perfect number is defined as a positive integer which is the sum of its positive divisors not including the number itself.
      For example: 6 is a perfect number because 6 = 1 + 2 + 3 (1, 2, 3 are divisors of 6)
      28 is also a perfect number: 28 = 1 + 2 + 4 + 7 + 14
     */

        //from должно быть больше нуля
        if (from < 1) {
            from = 1;
        }

        //для каждого числа делаем проверку, является ли он perfect number
        List<Integer> result = new ArrayList<>();

        for (int i = from; i <= to; i++) {

//            if ((i % 1000) == 0) {
//                System.out.println("Еще 1000 шагов миновало. Это был щаг " + i);
//            }

            if (isPerfectNumber(i)) {
//                System.out.println("Найдено! " + i + " is perfect number! ");
                result.add(i);
            }
        }



        return result;
    }

    //метод определяет, является ли число perfect number
    private static boolean isPerfectNumber (int num) {

        //получаем список делителей числа num
        List<Integer> dividors = getDividors(num);

        //складываем все числа в списке. Если сумма равна переданному числу - return true
        int sum = 0;
        for (int i = 0; i < dividors.size(); i++) {
            sum += dividors.get(i);
        }

        if (num == sum) {
            return true;
        }
        else {
            return false;
        }
    }

    //метод дает список делителей числа num, не включая само число
    private static List<Integer> getDividors(int num) {

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 1; i < num; i++) {
            if ((num % i) == 0) {
                result.add(i);
            }
        }


        return result;
    }


}
