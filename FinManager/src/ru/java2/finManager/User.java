package ru.java2.finManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Abilis on 04.04.2016.
 */
public class User {

    private String login;

    public String getLogin() {
        return login;
    }

    private String password;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    private ArrayList<Account> accounts;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }



    //метод пытается авторизовать пользователя
    public boolean autorization() {

        DbHelper dbHelper = DbHelper.getDbHelper();

        try {
            boolean isAutorizationSuccess = dbHelper.checkUser(this.login, this.password);

            if (isAutorizationSuccess) {
                ConsoleHelper.writeMessage("Авторизация прошла успешно!");
                return true;
            }
            else {
                ConsoleHelper.writeMessage("Неверный логин или пароль");
                return false;
            }


        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Не получилось выполнить запрос в БД. Попробуйте позднее");
            return false;
        }

    }


    //метод регистрирует нового пользователя
    public boolean registrationNewUser() {

        //здесь идет проверка, есть ли в БД пользователь с таким логином. Если нет - создается запись и return true
        //Если есть - выводится сообщение об уже имеющемся пользователе с таким логином и return false

        DbHelper dbHelper = DbHelper.getDbHelper();

        try {

            if (dbHelper.isUser(this.login)) {
                ConsoleHelper.writeMessage("Пользователь с именем " + this.login + " уже существует!");
                return false;
            } else {
                dbHelper.createUser(this.login, this.password);
                ConsoleHelper.writeMessage("Пользователь с именем " + this.login + " успешно зарегистрирован!");
                return true;
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Не получилось получить данные из базы либо записать в нее. Попробуйте позднее еще раз");
            e.printStackTrace();
            return false;
        }

    }

    //метод инициализирует поле accounts для текущего пользователя
    public ArrayList<Account> getAccounts() {

        DbHelper dbHelper = DbHelper.getDbHelper();

        //Определяем id текущего пользователя

        try {
            if (this.idUser == 0) {
                this.idUser = dbHelper.getIdUser(this.login);
            }

            if (this.accounts == null || this.accounts.isEmpty()) {
                //вытаскиваем из БД все аккаунты пользователя по его id
                this.accounts = dbHelper.getAccounts(this.idUser);
            }


        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Не удалось получить данные аккаунтов из БД");
        }

        return this.accounts;
    }

}
