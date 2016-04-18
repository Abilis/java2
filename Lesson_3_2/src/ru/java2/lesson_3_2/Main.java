package ru.java2.lesson_3_2;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 18.04.2016.
 */
public class Main {


    public static void main(String[] args) {

        JFrame mainWindow = new JFrame("Финансовый менеджер");

        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(600, 400);
        mainWindow.setLocationRelativeTo(null);

        mainWindow.setLayout(new GridBagLayout());

        JButton buttonLogin = new JButton("Залогиниться");
        JButton buttonRegistration = new JButton("Регистрация");

        JTextField textFieldLogin = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);

        mainWindow.add(textFieldLogin);
        mainWindow.add(buttonLogin);
        mainWindow.add(passwordField);
        mainWindow.add(buttonRegistration);



        mainWindow.setVisible(true);


    }


}
