package ru.java2.lesson4_1;


import ru.java2.lesson4_1.tasks.GetProbability;
import ru.java2.lesson4_1.tasks.PerfectNumbers;
import ru.java2.lesson4_1.tasks.RetainPositiveNumbers;

import java.util.Arrays;

/**
 * Created by Abilis on 25.04.2016.
 */
public class Main {

    public static void main(String[] args) {


        //Проверка PerfectNumbers
        System.out.println("Поиск perfect numbers...");
        System.out.println(PerfectNumbers.getPerfectNumbers(0, 9000));
        System.out.println();

        //Проверка RetainPositiveNumbers
        int[] test = new int[20];
        for (int i = 0; i < test.length; i++) {
            test[i] = (int) (Math.random() * test.length * 2 - test.length);
        }
        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(RetainPositiveNumbers.retainPositiveNumbers(test)));
        System.out.println();

        System.out.println("Проверка GetProbably");
        System.out.println(GetProbability.getProbability(10, 1));
    }

}
