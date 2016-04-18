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


        JPanel panel = new JPanel();
        mainWindow.add(panel, BorderLayout.SOUTH);


        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(600, 50));

        JTextField textFieldLogin = new JTextField(10);
        JPasswordField passwordFieldPass = new JPasswordField(10);

        JButton buttonLogin = new JButton("Залогиниться");
        JButton buttonRegistration = new JButton("Зарегистрироваться");

        panel.add(textFieldLogin);
        panel.add(passwordFieldPass);
        panel.add(buttonLogin);
        panel.add(buttonRegistration);



        mainWindow.setVisible(true);




    }


}
