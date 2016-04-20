package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Abilis on 20.04.2016.
 */
public class MainWindow {

    private User currentUser; //текущий пользователь
    private ArrayList<Account> accounts = new ArrayList<Account>(); //список аккаунтов текущего пользователя
    private Account currentAccount; //текущий аккаунт пользователя

    private JFrame mainWindowFrame = new JFrame("Финансовый менеджер");
    private Dimension dimensionMainWindowFrame = new Dimension(800, 600);

    //создаем метку, где находится имя пользователя
    private JLabel usernameLabel = new JLabel();

    //создаем метку, где отображается остаток на счету
    private JLabel ostatokLabel = new JLabel();

    //создаем метку, где будут отображаться сообщения об ошибках
    private JLabel mesLabel = new JLabel();

    //создаем метку, описывающую выпадающий список
    private JLabel listOfAccountLabel = new JLabel("Текущий аккаунт:");

    //создаем выпадающий список
    private JComboBox listOfAccounts;


    //создаем кнопку "создать новый аккаунт"
    private JButton addNewAccountButton = new JButton("Создать новый аккаунт");

    //создаем кнопку "добавить транзакцию"
    private JButton addRecordButton = new JButton("Добавить транзакцию");

    //создаем кнопку "закрыть"
    private JButton closeAppButton = new JButton("Закрыть");



    public MainWindow(User currentUser) {
        this.currentUser = currentUser;
        usernameLabel.setText(currentUser.getLogin());
        DbHelper dbHelper = DbHelper.getDbHerper();

        try {
            accounts = dbHelper.getAccounts(currentUser); //инициализация аккаунтов текущего пользователя

            currentUser.fillAccountsUser(accounts); //заполняем записями все аккаунты текущего пользователя

            currentUser.setAccounts(accounts); //записываем в поле текущего пользователя полученный список аккаунтов

            if (accounts.size() != 0) {
                currentAccount = accounts.get(0); //установка аккаунта по умолчанию
                ostatokLabel.setText("Баланс: " + String.valueOf(currentAccount.getOstatok()));
            }



        } catch (SQLException e) {
            e.printStackTrace();
            mesLabel.setText("Не удалось получить аккаунты для " + currentUser.getLogin());
        }


    }

    //инициализация формы
    public void init() {

        //установка настроек формы
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setResizable(false);
        mainWindowFrame.setSize(dimensionMainWindowFrame);
        mainWindowFrame.setLocationRelativeTo(null);

        mainWindowFrame.setLayout(new GridBagLayout());

        //установка настроек метки с сообщениями об ошибках
        mesLabel.setHorizontalAlignment(0);
        mesLabel.setForeground(Color.RED);

        //установка настроек выпадающего списка аккаунтов
        //инициализируем listOfAccounts массивом строк, описывающих аккаунт
        listOfAccounts = new JComboBox(currentUser.getArrOfAccounts());






        //расставляем компоненты

        //1 ряд, самый верх
        mainWindowFrame.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        mainWindowFrame.add(ostatokLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //2 ряд. Метка с сообщениями об ошибках
        mainWindowFrame.add(mesLabel, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //3 ряд. Метка, описывающая список аккаунтов
        mainWindowFrame.add(listOfAccountLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //4 ряд. Выпадающий список аккаунтов
        mainWindowFrame.add(listOfAccounts, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //5 ряд. Пролистываемый список записей


        //6 ряд. Кнопка "создать новый аккаунт"
        mainWindowFrame.add(addNewAccountButton, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //7 ряд. Кнопки "добавить транзакцию" и "закрыть"
        mainWindowFrame.add(addRecordButton, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        mainWindowFrame.add(closeAppButton, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //делаем форму видимой
        mainWindowFrame.setVisible(true);




        //обработчики событий

        //обработка нажатия кнопки "закрыть"
        closeAppButton.addActionListener(new CloseButtonActionListener());

        //обработка нажатия кнопки "создать новый аккаунт"
        addNewAccountButton.addActionListener(new AddNewAccountActionListener());

        //обработка нажатия кнопки "Добавить транзакцию"
        addRecordButton.addActionListener(new AddNewRecordButtonActionListener());

        //обработка нажатия клавиши "Энтер" на кнопке "Создать новый аккаунт"
        addNewAccountButton.addKeyListener(new AddNewAccountByPressEnterOnButonAddNewAccount());

        //обработка нажатия клавиши "Энтер" на кнопке "Добавить транзакцию"
        addRecordButton.addKeyListener(new AddNewRecordByPressEnterOnButtonAddNewRecord());

        //обработка нажатия клавиши "Энтер" на кнопке "Закрыть
        closeAppButton.addKeyListener(new CloseMainFrameByPressEnterOnButtonClose());


    }

    class CloseButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainWindowFrame.dispose();
        }
    }

    class AddNewAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //нужно создать новое окно, где пользователь может ввести/выбрать данные нового аккаунта
            AddNewAccount addNewAccount = new AddNewAccount(currentUser);
            mainWindowFrame.dispose();
            addNewAccount.init();

        }
    }

    class CloseMainFrameByPressEnterOnButtonClose extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new CloseButtonActionListener().actionPerformed(ae);
            }
        }
    }

    class AddNewAccountByPressEnterOnButonAddNewAccount extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewAccountActionListener ().actionPerformed(ae);
            }
        }
    }

    class AddNewRecordButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            mainWindowFrame.dispose();
            CreateNewRecord createNewRecord = new CreateNewRecord(currentUser);
            createNewRecord.init();

        }
    }

    class AddNewRecordByPressEnterOnButtonAddNewRecord extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewRecordButtonActionListener ().actionPerformed(ae);
            }
        }
    }

}
