package ru.java2.lesson_3_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by Abilis on 19.04.2016.
 */
public class LoginAndRegistrationFrame {

    //создаем форму для залогинивания и регистрации нового пользователя
    private JFrame loginForm = new JFrame("Финансовый менеджер");

    //создаем метки логина и пароля
    private JLabel loginLabel = new JLabel("Введите логин:");
    private JLabel passwordLabel = new JLabel("Введите пароль:");

    //создаем метку, куда будет сообщаться о неверном логине или пароле
    private JLabel wrongLoginOrPasswordLabel = new JLabel("Неверный логин или пароль");

    //создаем поля ввода логина и пароля
    private JTextField loginTestField = new JTextField(10);
    private JPasswordField passwordPasswordField = new JPasswordField(10);

    //создаем кнопки входа и регистрации
    private JButton loginButton = new JButton("Войти");
    private JButton registrationButton = new JButton("Зарегистрировать нового пользователя");

    //размер формы
    private Dimension dimension = new Dimension(300,210);


    public void initLoginAndRegistrationFrame() {

        //устанавливаем параметры формы
        loginForm.setSize(dimension);
        loginForm.setResizable(false);
        loginForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginForm.setLocationRelativeTo(null);


        //устанавливаем параметры метки о неверном логине или пароле
        wrongLoginOrPasswordLabel.setForeground(Color.RED);
        wrongLoginOrPasswordLabel.setHorizontalAlignment(0);
        wrongLoginOrPasswordLabel.setVisible(false);


        loginForm.setLayout(new GridBagLayout());

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

        //метка для отображения неправильного ввода логина или пароля
        loginForm.add(wrongLoginOrPasswordLabel, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));

        //кнопки залогинивания и регистрации
        loginForm.add(loginButton, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));

        loginForm.add(registrationButton, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));


        //Обработка событий
        //Обработки нажатия на кнопку залогинивания
        loginButton.addActionListener(new LoginButtonActionListener());

        //обработка нажатия на кнопку создания нового пользователя
        registrationButton.addActionListener(new RegistrationButtonActionListener());


        //делаем форму видимой
        loginForm.setVisible(true);
    }

    class LoginButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String login = loginTestField.getText();
            String password = passwordPasswordField.getText();

            System.out.println("Попытка залогиниться с логином " + login + " и паролем " + password);



            //Элемент случайности - неверный логин или пароль
            if ((new Date().getTime() & 1) == 1) {
                wrongLoginOrPasswordLabel.setVisible(true);
            }
            else {
                wrongLoginOrPasswordLabel.setVisible(false);

                //логин и пароль совпадают
                User currentUser = new User(login, password);

                //Закрываем форму
                loginForm.dispose();

                //создаем новую форму и передаем туда управление
                MainWindowFrame mainWindowFrame = new MainWindowFrame();
                mainWindowFrame.initMainWindowFrame(currentUser);
            }

        }
    }

    class RegistrationButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Попытка создать нового пользователя");
        }
    }

}