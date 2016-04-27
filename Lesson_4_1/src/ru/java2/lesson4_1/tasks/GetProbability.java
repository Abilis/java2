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

        //общее число комбинаций при бросании Y раз
        double totalCombinations = Math.pow(6, Y);

        //по крайней мере Х 4s означает, что выпадет по крайней мере Х раз число 4
        //число комбинаций при 1 броске, когда выпадает 4: 1
        //число комбинаций при Х бросках, когда выпадает 4: 4 * Х


        //вероятность того, что при Y бросках число 4 не выпадет ни разу 5 / totalCombinations
        //вероятность того, что при У бросках число 4 выпадет ровно сколько угодно раз 1 - (5 / totalCombinations)
        //вероятность выпадения числа 4 на каждом броске: 1 / 6

        double tmp = 0;

        //число комбинаций, когда выпадает 4 Х раз при У бросках

        int fourCombinations = 0;
        while (X <= Y) {
            fourCombinations += 4 * X;
            X++;
        }

        tmp = fourCombinations / totalCombinations;



        return tmp;
    }

}
