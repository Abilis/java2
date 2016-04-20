package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;

import javax.swing.*;
import java.awt.*;
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


    public MainWindow(User currentUser) {
        this.currentUser = currentUser;
        usernameLabel.setText(currentUser.getLogin());
        DbHelper dbHelper = DbHelper.getDbHerper();

        try {
            accounts = dbHelper.getAccounts(currentUser); //инициализация аккаунтов текущего пользователя

            currentUser.fillAccountsUser(accounts); //заполняем записями все аккаунты текущего пользователя

            if (accounts.size() != 0) {
                currentAccount = accounts.get(0); //установка аккаунта по умолчанию
                ostatokLabel.setText(String.valueOf(currentAccount.getOstatok()));
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
        mesLabel.setHorizontalAlignment(0);;
        mesLabel.setForeground(Color.RED);



        //расставляем компоненты

        //1 ряд, самый верх
        mainWindowFrame.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        mainWindowFrame.add(ostatokLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //2 ряд. Метка с сообщениями об ошибках
        mainWindowFrame.add(mesLabel, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //3 ряд. Выпадающий список аккаунтов


        //4 ряд. Пролистываемый список записей

        

        //делаем форму видимой
        mainWindowFrame.setVisible(true);





    }
}
