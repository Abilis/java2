package ru.java2.finManager2.gui;

import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;
import ru.java2.finManager2.exceptions.DontCreateNewUserException;
import ru.java2.finManager2.exceptions.ExistSuchUserException;
import ru.java2.finManager2.utils.Md5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
        registrationNewUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationNewUserFrame.setSize(dimension);
        registrationNewUserFrame.setResizable(false);
        registrationNewUserFrame.setLocationRelativeTo(null);

        registrationNewUserFrame.setLayout(new GridBagLayout());

        //установка настроек метки для вывода об ошибках
        messagesLabel.setForeground(Color.RED);
        messagesLabel.setHorizontalAlignment(0);

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
        registrationNewUserFrame.add(messagesLabel, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //5 ряд - две кнопки
        registrationNewUserFrame.add(createNewUserButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        registrationNewUserFrame.add(cancelButton, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //делаем форму видимой
        registrationNewUserFrame.setVisible(true);


        //Обработка нажатия кнопок
        createNewUserButton.addActionListener(new CreateNewUserButtonActionListener());

        confirnPasswordField.addKeyListener(new CreateNewUserByPressEnterInConfirmPasswordFieald());

        cancelButton.addActionListener(new CancelButtonActionListener());

    }


    class CreateNewUserButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //вытаскиваем строки из полей ввода и тримим их
            String login = loginTextField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirnPasswordField.getText();

            login = login.trim();
            password = password.trim();
            confirmPassword = confirmPassword.trim();

            if (login.equals("") || password.equals("") || confirmPassword.equals("")) {
                return;
            }

            if (!password.equals(confirmPassword)) {
                messagesLabel.setText("Пароли не совпадают");
                return;
            }

            //если введены корректные данные, то получаем md5 пароля и создаем нового пользователя
            try {
                password = Md5.getMd5(password);
            } catch (NoSuchAlgorithmException e1) {
                messagesLabel.setText("Не получилось получить md5 пароля. Попробуйте еще раз");
            }

            User newUser = new User(login, password);

            //и создаем этого пользователя в БД
            DbHelper dbHelper = DbHelper.getDbHerper();
            try {
                dbHelper.addUser(newUser);
            } catch (DontCreateNewUserException e1) {
                //не удалось создать нового пользователя
                messagesLabel.setText(e1.getMessage());
                return;
            } catch (SQLException e1) {
                //что-то еще не так с БД
                messagesLabel.setText("Что-то не так с БД");
                return;
            } catch (ExistSuchUserException e1) {
                messagesLabel.setText(e1.getMessage());
                return;
            }

            //если дошли сюда - новый пользователь успешно создался
            //поэтому закрываем это окно и открываем основное окно приложения
            registrationNewUserFrame.dispose();

            MainWindow mainWindow = new MainWindow(newUser);
            mainWindow.init();

        }
    }

    class CancelButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //просто закрываем форму
            registrationNewUserFrame.dispose();

            //и снова создаем форму логина и регистрации
            LoginAndRegistration loginAndRegistration = new LoginAndRegistration();
            loginAndRegistration.init();
        }
    }

    class CreateNewUserByPressEnterInConfirmPasswordFieald implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new CreateNewUserButtonActionListener().actionPerformed(ae);
            }
        }
    }
}
