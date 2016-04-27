package ru.java2.lesson4_1;


import ru.java2.lesson4_1.tasks.PerfectNumbers;

/**
 * Created by Abilis on 25.04.2016.
 */
public class Main {

    public static void main(String[] args) {


        //Проверка PerfectNumbers
        System.out.println("Поиск perfect numbers...");
        System.out.println(PerfectNumbers.getPerfectNumbers(0, 20000));

    }

}
