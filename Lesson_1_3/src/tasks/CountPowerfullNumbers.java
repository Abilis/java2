package tasks;

import java.util.ArrayList;

/**
 * Created by Сергей on 27.03.2016.
 */
public class CountPowerfullNumbers {

  //   public static ArrayList<Integer> countPowerfulNumbers(int from, int to) {
    public static int countPowerfulNumbers(int from, int to) {
        /*
          A powerful number is a positive integer m that for every prime number p dividing m, p*p also divides m.

          (a prime number (or a prime) is a natural number that has exactly two (distinct) natural number divisors,
          which are 1 and the prime number itself, the first prime numbers are: 2, 3, 5, 7, 11, 13, ...)

          The first powerful numbers are: 1, 4, 8, 9, 16, 25, 27, 32, 36, ...

          Please implement this method to
          return the count of powerful numbers in the range [from..to] inclusively.
         */


        //будет цикл от from до to включая границы

        //для каждого числа находим все его делители, которые являются простыми числами

        //если число делится нацело на все квадраты своих простых делителей - это есть полнократное число

        ArrayList<Integer> powerfulNumbers = new ArrayList<Integer>();

        for (int i = from; i <= to; i++) {

            //получаем список делителей
            ArrayList<Integer> dividers = getDividers(i);

            //получаем список простых чисел из делителей
            ArrayList<Integer> primes = getPrimes(dividers);

            //получаем список квадратов чисел, находящихся в списке primes
            ArrayList<Integer> squarePrimes = getSquareForEachNumber(primes);

            //бежим по списку квадратов. Если i делится нацело на все из них, то мы нашли полнократное число

            boolean isPowerful = true; //флаг полнократного числа. Станет false при провале любой проверки

            for (Integer sp : squarePrimes) {
                if (i % sp != 0) {
                    //если есть остаток от деления, то i уже не полнократное число и выходим из цикла проверки
                    isPowerful = false;
                    break;
                }
            }

            if (isPowerful) {
                powerfulNumbers.add(i);
            }
        }

    //    return powerfulNumbers;

        //возвращаем количество найденных полнократных чисел

        return powerfulNumbers.size();
    }


    //метод возвращает список чисел, которые являются делителями входного числа
    private static ArrayList<Integer> getDividers(int a) {

        ArrayList<Integer> dividers = new ArrayList<Integer>();

        for (int i = 1; i <= a; i++) {
            if (a % i == 0) {
                dividers.add(i);
            }
        }

        return dividers;
    }

    //метод возвращает список простых чисел, которые есть в переданном списке
    private static ArrayList<Integer> getPrimes(ArrayList<Integer> list) {

        ArrayList<Integer> primes = new ArrayList<Integer>();
        //проверяем каждое число во входном списке
        for (Integer number : list) {

            int countDividers = 0;
            //бежим от 1 до самого числа. Если исходное число делится нацело на число, равное счетчику, делаем countDividers++;
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    countDividers++;
                }
            }

            //если countDividers == 2, то number - простое
            if (countDividers == 2) {
                primes.add(number);
            }
        }

        return primes;
    }

    //метод каждое число во входном списке возводит в квадрат и возвращает полученный новый список
    private static ArrayList<Integer> getSquareForEachNumber(ArrayList<Integer> list) {

        ArrayList<Integer> squareEachNumber = new ArrayList<Integer>();

        for (Integer number : list) {
            squareEachNumber.add((int)(Math.pow(number, 2)));
        }
        return squareEachNumber;
    }


}
