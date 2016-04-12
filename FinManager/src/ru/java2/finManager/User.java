package ru.java2.finManager;

import java.util.ArrayList;

/**
 * Created by Abilis on 04.04.2016.
 */
public class User {

    private static final String TRUE_LOGIN = "login";
    private static final String TRUE_PASSWORD = "pass";


    private String login;
    private String password;

    private ArrayList<Account> accounts;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }



    //метод пытается авторизовать пользователя
    public boolean autorization() {

        if (this.login.equals(TRUE_LOGIN) && this.password.equals(TRUE_PASSWORD)) {
            ConsoleHelper.writeMessage("Авторизация прошла успешно");
            return true;
        } else {
            ConsoleHelper.writeMessage("Неверный логин или пароль");
            return false;
        }
    }

    //метод регистрирует нового пользователя
    public boolean registrationNewUser() {

        //здесь идет проверка, есть ли в БД пользователь с таким логином. Если нет - создается запись и return true
        //Если есть - выводится сообщение об уже имеющемся пользователе с таким логином и return false

        if (!this.login.equals(TRUE_LOGIN)) {

            //запись в БД
            ConsoleHelper.writeMessage("Пользователь с именем " + this.login + " успешно зарегистрирован!");
            return true;
        }

        ConsoleHelper.writeMessage("Пользоветаль с именем " + this.login + " уже существует!");
        return false;
    }

}
