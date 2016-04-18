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
        mainWindow.setLayout(new GridBagLayout());


        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);

        JTextField textFieldLogin = new JTextField(10);
        JButton buttonLogin = new JButton("Залогиниться");

        panel.add(textFieldLogin);
        panel.add(buttonLogin);
        mainWindow.add(panel);

        mainWindow.setVisible(true);




    }


}
