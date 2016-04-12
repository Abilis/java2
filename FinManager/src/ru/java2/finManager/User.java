package ru.java2.finManager;

import java.util.ArrayList;

/**
 * Created by Abilis on 04.04.2016.
 */
public class User {

    private final String TRUE_LOGIN = "testlogin";
    private final String TRUE_PASSWORD = "testpassword";


    private String login;
    private String password;

    private ArrayList<Account> accounts;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public boolean autorization() {

        if (login.equals(TRUE_LOGIN) && password.equals(TRUE_PASSWORD)) {
            return true;
        }
        else {
            return false;
        }

    }

}
