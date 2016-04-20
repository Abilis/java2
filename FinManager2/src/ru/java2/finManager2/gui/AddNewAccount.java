package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;
import ru.java2.finManager2.exceptions.ExistSuchAccountException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

/**
 * Created by Abilis on 20.04.2016.
 */
public class AddNewAccount {

    private User currentUser;

    //создаем диалоговое окно
    private JFrame addNewAccountFrame = new JFrame();
    private Dimension dimensionAddNewAccountFrame = new Dimension(330, 200);

    //создаем метку-описание того, что здесь происходит
    private JLabel descriptionFrameLabel = new JLabel();

    //создаем метки для обозначения описания аккаунта и остатка на счету
    private JLabel descriptionNewAccLabel = new JLabel("Название нового аккаунта:");
    private JLabel ostatokNewAccLabel = new JLabel("Начальная сумма на счету:");

    //создаем поля ввода для описания аккаунта и остатка на счету
    private JTextField descriptionNewAccTextField = new JTextField(10);
    private JTextField ostatokNewAccTextField = new JTextField(10);

    //создаем пару кнопок: "добавить новый аккаунт" и "закрыть"
    private JButton addNewAccountButton = new JButton("Создать новый аккаунт");
    private JButton closeAddNewAccountFrameButton = new JButton("Закрыть");

    //создаем метку для отображения сообщений об ошибках
    private JLabel messagesLabel = new JLabel();


    public AddNewAccount(User currentUser) {
        this.currentUser = currentUser;
    }

    public void init() {

        //установка настроек формы
        addNewAccountFrame.setSize(dimensionAddNewAccountFrame);
        addNewAccountFrame.setResizable(false);
        addNewAccountFrame.setLocationRelativeTo(null);
        addNewAccountFrame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        addNewAccountFrame.setLayout(new GridBagLayout());

        //установка настроек метки-описания
        descriptionFrameLabel.setText("Создание нового аккаунта для пользователя " + currentUser.getLogin());

        //установка настроек метки об ошибках
        messagesLabel.setHorizontalAlignment(0);
        messagesLabel.setForeground(Color.RED);

        //расставляем компоненты

        //1 ряд. Метка-описание текущего пользователя
        addNewAccountFrame.add(descriptionFrameLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //2 ряд. Метка и поле ввода описания нового аккаунта
        addNewAccountFrame.add(descriptionNewAccLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        addNewAccountFrame.add(descriptionNewAccTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //3 ряд. Метка и поле ввода остатка на счету
        addNewAccountFrame.add(ostatokNewAccLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        addNewAccountFrame.add(ostatokNewAccTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //4 ряд. Здесь будут отображаться различные сообщения об ошибках
        addNewAccountFrame.add(messagesLabel, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //5 ряд. Две кнопки: "создать новый аккаунт" и "отмена"
        addNewAccountFrame.add(addNewAccountButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        addNewAccountFrame.add(closeAddNewAccountFrameButton, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //делаем форму видимой
        addNewAccountFrame.setVisible(true);


        //обработчики

        //обработка нажатия на кнопку "Закрыть"
        closeAddNewAccountFrameButton.addActionListener(new CloseNewAccountFrameActionListener());

        //обработка нажатия на кнопку "Создать новый аккаунт"
        addNewAccountButton.addActionListener(new AddNewAccountActionListener());

        //обработка нажатия клавиши "Энтер" в поле ввода названия нового аккаунта
        descriptionNewAccTextField.addKeyListener(new AddNewAccountByPressEnterInDescriptionNewAccountTextField());

        //обработка нажатия клавиши "Энтер" в поле ввода начальной суммы на счету
        ostatokNewAccTextField.addKeyListener(new AddNewAccountByPressEnterInOstatokNewAccountTextField());

        //обработка нажатия клавиши "Энтер" на кнопке "Создать новый аккаунт"
        addNewAccountButton.addKeyListener(new AddNewAccountByPressEnterOnCreateNewAccountButton());

        //обработка нажатия клавиши "Энтер" на кнопке "Закрыть"
        closeAddNewAccountFrameButton.addKeyListener(new CloseNewAccountFrameByPressEnterOnButtonCloseNewAccountFrame());

    }

    class CloseNewAccountFrameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addNewAccountFrame.dispose();
        }
    }

    class AddNewAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String descriptionNewAccount = descriptionNewAccTextField.getText();
            String ostatokNewAccount = ostatokNewAccTextField.getText();



            //Тримим полученные строки
            descriptionNewAccount = descriptionNewAccount.trim();
            ostatokNewAccount = ostatokNewAccount.trim();

            //если хотя бы в одном поле ничего нет - выводим сообщение об ошибке и заканчиваем обработку
            if (descriptionNewAccount.length() == 0 || ostatokNewAccount.length() == 0) {
                messagesLabel.setText("Некорректные данные!");
                return;
            }

            //преобразуем остаток в целое число

            int ostatok = 0;
            try {
                ostatok = Integer.parseInt(ostatokNewAccount);
            } catch (NumberFormatException e2) {
                messagesLabel.setText("Некорректные данные!");
                return;
            }

            //если проверки пройдены - создаем новый аккаунт для текущего пользователя и закрываем это окно

            Account account = new Account(descriptionNewAccount, ostatok);

            DbHelper dbHelper = DbHelper.getDbHerper();
            try {
                dbHelper.addAccount(currentUser, account);

                //если дошли сюда - значит, новый аккаунт успешно создался. Необходимо закрыть эту форму
                addNewAccountFrame.dispose();

                //и заново нарисовать главное окно приложения
                MainWindow mainWindow = new MainWindow(currentUser);
                mainWindow.init();

            } catch (ExistSuchAccountException e1) {
                //такой аккаунт уже существуют
                messagesLabel.setText(e1.getMessage());

            } catch (SQLException e1) {
                //ошибка БД
                messagesLabel.setText(e1.getMessage());
            }
        }
    }

    class CloseNewAccountFrameByPressEnterOnButtonCloseNewAccountFrame extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new CloseNewAccountFrameActionListener().actionPerformed(ae);
            }
        }
    }

    class AddNewAccountByPressEnterInDescriptionNewAccountTextField extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewAccountActionListener().actionPerformed(ae);
            }
        }
    }

    class AddNewAccountByPressEnterInOstatokNewAccountTextField extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewAccountActionListener().actionPerformed(ae);
            }
        }
    }

    class AddNewAccountByPressEnterOnCreateNewAccountButton extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewAccountActionListener().actionPerformed(ae);
            }
        }
    }

}
