package tasks;

import java.util.Arrays;

/**
 * Created by Сергей on 16.04.2016.
 */
public class IsPalindrome {

    public static boolean isPalindrome(String s) {
        /*
          Definition: A palindrome is a string that reads the same forward and backward.
          For example, "abcba" is a palindrome, "abab" is not.
          Please implement this method to
          return true if the parameter is a palindrome and false otherwise.
         */

        //тут хотелось бы использовать метод reverseArray, но из-за того, что он получает и возвращает Object
        //реализация выглядит не очень хорошей. С дженериками было бы проще, наверное.
        //Поэтому пойдем своим путем

        //Преобразуем входную строку в массив символов
        char[] origin = s.toCharArray();

        //создаем новый массив символов, куда записываем перевернутый массив origin
        char[] reversed = Arrays.copyOf(origin, origin.length);

        for (int i = 0; i < reversed.length / 2; i++) {
            char tmp = reversed[i];
            reversed[i] = reversed[reversed.length - 1 - i];
            reversed[reversed.length - 1 - i] = tmp;
        }

        //сравниваем эти два массива

        boolean eqial = true;

        for (int i = 0; i < origin.length; i++) {
            if (origin[i] != reversed[i]) {
                eqial = false;
                break;
            }
        }

        return eqial;
    }
}
