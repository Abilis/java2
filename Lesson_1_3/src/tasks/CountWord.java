package tasks;

/**
 * Created by Сергей on 27.03.2016.
 */
public class CountWord {

    public static int countWords(String s) {
        /*
          Please implement this method to return the word count in a given String.

          The words in the parameter String can be separated from each other by any nubmer
          of whitespace symbols (Character.isWhitespace()).
         */
        if (s == null) {
            return 0;
        }

        //do split into array by reget "\s+"

        String[] strAsArr = s.split("\\s+");


        //return lenght this array


        return strAsArr.length;
    }
}
