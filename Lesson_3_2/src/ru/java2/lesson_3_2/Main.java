package ru.java2.lesson_3_2;


import javax.swing.*;

/**
 * Created by Abilis on 18.04.2016.
 */
public class Main {


    public static void main(String[] args) {

        JFrame frameExternal = new JFrame("Внешняя форма");

        frameExternal.setSize(600, 400);
        frameExternal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameExternal.setLocationRelativeTo(null);


        frameExternal.setVisible(true);


        JFrame frameInternal = new JFrame("Внутренняя форма");
        frameInternal.setSize(300, 100);
        frameInternal.setLocationRelativeTo(frameExternal);


        frameInternal.setVisible(true);




    }


}
