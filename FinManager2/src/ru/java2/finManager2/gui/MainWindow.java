package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Abilis on 20.04.2016.
 */
public class MainWindow {

    private User currentUser; //текущий пользователь
    private ArrayList<Account> accounts = new ArrayList<Account>(); //список аккаунтов текущего пользователя

    private JFrame mainWindowFrame = new JFrame("Финансовый менеджер");
    private Dimension dimensionMainWindowFrame = new Dimension(800, 600);

    //создаем метку, где находится имя пользователя
    private JLabel usernameLabel = new JLabel();


    public MainWindow(User currentUser) {
        this.currentUser = currentUser;
        usernameLabel.setText(currentUser.getLogin());
        DbHelper dbHelper = DbHelper.getDbHerper();
        accounts = dbHelper.getAccounts(currentUser);
    }

    //инициализация формы
    public void init() {

        //установка настроек формы
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setResizable(false);
        mainWindowFrame.setSize(dimensionMainWindowFrame);
        mainWindowFrame.setLocationRelativeTo(null);

        mainWindowFrame.setLayout(new GridBagLayout());

        //установка настроек метки



        //расставляем компоненты
        mainWindowFrame.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));


        //делаем форму видимой
        mainWindowFrame.setVisible(true);

    }
}
