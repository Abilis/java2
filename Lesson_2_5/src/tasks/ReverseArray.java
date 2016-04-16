package tasks;

import java.util.Arrays;

/**
 * Created by Сергей on 16.04.2016.
 */
public class ReverseArray {
    public static Object[] reverseArray(Object[] a) {
        /*
          Please implement this method to
          return a new array where the order of elements has been reversed from the original
          array.
         */

        if (a == null) {
            return null;
        }

        //создаем новый массив, который является копией входного
        Object[] newArr = Arrays.copyOf(a, a.length);

        //теперь переворачиваем новый массив и возвращаем его
        for (int i = 0; i < newArr.length / 2; i++) {

            Object tmp = newArr[i];
            newArr[i] = newArr[newArr.length - 1 - i];
            newArr[newArr.length - 1 - i] = tmp;
        }

        return newArr;
    }
}
