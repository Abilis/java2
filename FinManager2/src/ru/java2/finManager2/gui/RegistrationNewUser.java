package ru.java2.finManager2.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 20.04.2016.
 */
public class RegistrationNewUser {

    private JFrame registrationNewUserFrame = new JFrame("Регистрация нового пользователя");

    //опрередение размера формы
    private Dimension dimension = new Dimension(320, 200);

    //создаем метки
    JLabel loginLabel = new JLabel("Логин:");
    JLabel passwordLabel = new JLabel("Пароль:");
    JLabel confirmPasswordLabel = new JLabel("Повторите пароль:");
    JLabel messagesLabel = new JLabel();

    //создаем поля ввода
    JTextField loginTextField = new JTextField(10);
    JPasswordField passwordField = new JPasswordField(10);
    JPasswordField confirnPasswordField = new JPasswordField(10);

    //создаем кнопки
    JButton createNewUserButton = new JButton("Зарегистрироваться");
    JButton cancelButton = new JButton("Отмена");

    //инициализация формы регистрации
    public void init() {

        //установка настроек формы
        registrationNewUserFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        registrationNewUserFrame.setLocationRelativeTo(null);
        registrationNewUserFrame.setSize(dimension);
        registrationNewUserFrame.setResizable(false);

        registrationNewUserFrame.setLayout(new GridBagLayout());


        //Расставляем компоненты

        //расставляем 1 ряд
        registrationNewUserFrame.add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        registrationNewUserFrame.add(loginTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //расставляем 2 ряд
        registrationNewUserFrame.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        registrationNewUserFrame.add(passwordField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //расставляем 3 ряд
        registrationNewUserFrame.add(confirmPasswordLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        registrationNewUserFrame.add(confirnPasswordField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //4 ряд будет меткой для сообщений об ошибках
        registrationNewUserFrame.add(messagesLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //5 ряд - две кнопки
        registrationNewUserFrame.add(createNewUserButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        registrationNewUserFrame.add(cancelButton, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //делаем форму видимой
        registrationNewUserFrame.setVisible(true);
    }


    
}
