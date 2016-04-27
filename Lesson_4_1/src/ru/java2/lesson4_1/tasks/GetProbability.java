package ru.java2.lesson4_1.tasks;

/**
 * Created by Abilis on 27.04.2016.
 */
public class GetProbability {

    public static double getProbability(int Y, int X) {
    /*
      If you roll Y standard six-sided dice, what’s the probability that you get at least X 4s?
      To calculate that you should divide the number of comibnations with X or more 4s
      by the total number of possible combinations.
     */



        double result = 0.0;


        while (X <= Y) {
            double numCombin = getFact(Y) / (getFact(X) * getFact(Y - X));
            result += numCombin * Math.pow((1.0 / 6.0), (double) X) * Math.pow((5.0 / 6.0), (double) (Y - X));
            X++;
        }

        return result;

    }

    private static int getFact(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }
        else {
            return n * getFact(n - 1);
        }
    }



}
