package ru.java2.lesson_3_2;

import javax.swing.*;

/**
 * Created by Abilis on 18.04.2016.
 */
public class Main {


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setTitle("Тестовая форма");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        frame.setVisible(true);

        frame.setState(JFrame.ICONIFIED);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


}
