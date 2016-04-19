package ru.java2.finManager2.gui;

import ru.java2.finManager2.User;
import ru.java2.finManager2.exceptions.NoSuchUserException;
import ru.java2.finManager2.utils.Md5;
import ru.java2.finManager2.database.DbHelper;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Abilis on 19.04.2016.
 */
public class LoginAndRegistration {

    //создаем форму
    private JFrame loginAndRegistrationFrame = new JFrame("Финансовый менеджер");

    //создаем метки, сообщающие о необходимости ввести логин, пароль, а также скрытую о невером логине и/или пароле
    private JLabel loginLabel = new JLabel("Логин:");
    private JLabel passwordLabel = new JLabel("Пароль:");
    private JLabel wrongLoginOrPasswordLabel = new JLabel();

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
        loginAndRegistrationFrame.setSize(dimension);
        loginAndRegistrationFrame.setResizable(false);
        loginAndRegistrationFrame.setLocationRelativeTo(null);

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


        //делаем форму видимой
        loginAndRegistrationFrame.setVisible(true);


        //обработчики

        //обработка нажатия кнопки логина
        loginButton.addActionListener(new LoginButtonActionListener());

        //обработка нажатия кнопки регистрация
        registrationButton.addActionListener(new RegistrationButtonActionListener());

    }

    class LoginButtonActionListener implements ActionListener {
        //метод, обрабатывающий нажатие кнопки "войти"
        @Override
        public void actionPerformed(ActionEvent e) {

            //получаем данные из из полей ввода
            String login = loginTextField.getText();
            String password = passwordPasswordField.getText();

            //обрезаем пробелы с обеих сторон
            login = login.trim();
            password = password.trim();

            //если поля пустые = заканчиваем обработку
            if (login.equals("") || password.equals("")) {
                return;
            }

            //Преобразуем пароль в хэш-мд5
            try {
                password = Md5.getMd5(password);
            } catch (NoSuchAlgorithmException e1) {
                wrongLoginOrPasswordLabel.setText("Не получилось получить md5 пароля. Попробуйте еще раз");
            }

            //создаем пользователя на основе введенных данных
            User currentUser = new User(login, password);

            //вытаскиваем пользователя из БД по имени
            DbHelper dbHelper = DbHelper.getDbHerper();
            User userFromDb = null;

            try {
                userFromDb = dbHelper.getUser(login);
            } catch (SQLException e1) {
                //не получилось приконнектиться к базе
                wrongLoginOrPasswordLabel.setText("Не удалось получить данные из БД");
            } catch (NoSuchUserException e1) {
                //пользователь с таким логином не найден. Прекращаем обработку
                wrongLoginOrPasswordLabel.setText(e1.getMessage());
                return;
            }

            //сравниваем пользователей
            if (currentUser.equals(userFromDb)) {
                //если все нормально - создаем новую форму, а эту закрываем.
                loginAndRegistrationFrame.dispose();

                MainWindow mainWindow = new MainWindow(currentUser);
                mainWindow.init();
            }
            else {
                //иначе - сообщаем о неверном логине или пароле
                wrongLoginOrPasswordLabel.setText("Неверный логин или пароль!");
            }
        }
    }

    class RegistrationButtonActionListener implements ActionListener {

        //метод, обрабатывающий нажатие кнопки "Зарегистрироваться"
        @Override
        public void actionPerformed(ActionEvent e) {

            RegistrationNewUser registrationNewUser = new RegistrationNewUser();
            registrationNewUser.init();

            //закрываем эту форму
            loginAndRegistrationFrame.dispose();

        }
    }



}
