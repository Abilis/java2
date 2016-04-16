package tasks;

/**
 * Created by Сергей on 16.04.2016.
 */
public class getCountOfOnes {

    public static int getCountOfOnes(int n) {
        /*
         Please implement this method to
         return the number of '1's in the binary representation of n
         for any integer n, where n > 0

         Example: for n=6 the binary representation is '110' and the number of '1's in that
         representation is 2

        */

        //строковое представление двоичной записи целого числа n
        String nAsBinaryString = Integer.toBinaryString(n);

        //теперь считаем количество единиц
        int countOfOnes = 0;

        char[] binaryNAsCharArr = nAsBinaryString.toCharArray();

        for (char sym : binaryNAsCharArr) {
            if (sym == '1') {
                countOfOnes++;
            }
        }


        return countOfOnes;
    }
}
