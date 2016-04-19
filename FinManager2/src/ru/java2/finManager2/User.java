package ru.java2.finManager2;

import java.util.ArrayList;

/**
 * Created by Abilis on 19.04.2016.
 */
public class User {

    private String login;
    private String password;
    private ArrayList<Account> accounts;

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';

    }
}
