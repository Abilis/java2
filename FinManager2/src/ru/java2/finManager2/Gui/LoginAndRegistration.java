package ru.java2.finManager2.Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 19.04.2016.
 */
public class LoginAndRegistration {

    //создаем форму
    private JFrame loginAndRegistrationFrame = new JFrame("Финансовый менеджер");

    //создаем метки, сообщающие о необходимости ввести логин, пароль, а также скрытую о невером логине и/или пароле
    private JLabel loginLabel = new JLabel("Логин:");
    private JLabel passwordLabel = new JLabel("Пароль:");
    private JLabel wrongLoginOrPasswordLabel = new JLabel("Неверный логин или пароль!");

    //создаем поля ввода логина и пароля
    private JTextField loginTextField = new JTextField();
    private JPasswordField passwordPasswordField = new JPasswordField();

    //создаем кнопки "залогиниться" и "создать нового пользователя"
    private JButton loginButton = new JButton("Войти");
    private JButton registrationButton = new JButton("Зарегистрироваться");

    //опрередение размера формы
    private Dimension dimension = new Dimension(280, 180);

    //метод инициализирует окно логина и регистрации
    public void init() {

        //установка настроек формы
        loginAndRegistrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginAndRegistrationFrame.setLocationRelativeTo(null);
        loginAndRegistrationFrame.setSize(dimension);
        loginAndRegistrationFrame.setResizable(false);

        loginAndRegistrationFrame.setLayout(new GridBagLayout());

        //рапологаем компоненты на форме
        loginAndRegistrationFrame.add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        loginAndRegistrationFrame.add(loginTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        loginAndRegistrationFrame.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        loginAndRegistrationFrame.add(passwordPasswordField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        loginAndRegistrationFrame.add(wrongLoginOrPasswordLabel, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        loginAndRegistrationFrame.add(loginButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        loginAndRegistrationFrame.add(registrationButton, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //настройка компонентов
        //настройка метки о неверном логине или пароле
        wrongLoginOrPasswordLabel.setForeground(Color.RED);
        wrongLoginOrPasswordLabel.setHorizontalAlignment(0);
        wrongLoginOrPasswordLabel.setVisible(false);


        //делаем форму видимой
        loginAndRegistrationFrame.setVisible(true);


        //обработчики

        //обработка нажатия кнопки логина


        //обработка нажатия кнопки регистрация


    }
}
