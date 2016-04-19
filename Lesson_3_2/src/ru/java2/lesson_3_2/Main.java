package ru.java2.lesson_3_2;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 18.04.2016.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        JFrame loginForm = new JFrame("Финансовый менеджер");
        loginForm.setSize(600, 400);
        loginForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginForm.setLocationRelativeTo(null);

        loginForm.setLayout(new GridBagLayout());

        //создаем метки логина и пароль
        JLabel loginLabel = new JLabel("Введите логин:");
        JLabel passwordLabel = new JLabel("Введите пароль:");

        //создаем поля ввода логина и пароль
        JTextField loginTestField = new JTextField(10);
        JPasswordField passwordPasswordField = new JPasswordField(10);

        //создаем кнопки входа и регистрации
        JButton loginButton = new JButton("Войти");
        JButton registrationButton = new JButton("Зарегистрировать нового пользователя");

        //расставляем компоненты на форме

        //метка и поле ввода логина
        loginForm.add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));

        loginForm.add(loginTestField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));

        //метка и поле ввода пароля
        loginForm.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));

        loginForm.add(passwordPasswordField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));

        //кнопки залогинивания и регистрации
        loginForm.add(loginButton, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));

        loginForm.add(registrationButton, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));


        loginForm.pack();
        loginForm.setVisible(true);

    }


}
