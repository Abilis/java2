package ru.java2.lesson_3_2;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 18.04.2016.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        JFrame mainWindow = new JFrame("Финансовый менеджер");

        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(600, 400);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.BLUE);
        panel2.setBackground(Color.YELLOW);
        panel3.setBackground(Color.ORANGE);
        panel4.setBackground(Color.GREEN);
        panel5.setBackground(Color.RED);

        mainWindow.add(panel1, BorderLayout.PAGE_START);
        mainWindow.add(panel2, BorderLayout.PAGE_END);
        mainWindow.add(panel3, BorderLayout.CENTER);
        mainWindow.add(panel4, BorderLayout.LINE_END);
        mainWindow.add(panel5, BorderLayout.LINE_START);

        panel3.setLayout(new BorderLayout());

        JButton buttonLogin = new JButton("Залогиниться");
        JButton buttonRegistration = new JButton("Зарегистрироваться");

        panel3.add(buttonLogin, BorderLayout.NORTH);
        panel3.add(buttonRegistration, BorderLayout.SOUTH);

        mainWindow.setVisible(true);




    }


}
