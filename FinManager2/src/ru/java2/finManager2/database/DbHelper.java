package ru.java2.finManager2.database;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;

import java.sql.Connection;
import java.util.Set;

/**
 * Created by Abilis on 19.04.2016.
 */
public class DbHelper implements DataStore {

    private Connection connection;
    private final String URL = "";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private DbHelper() {

    }

    private Connection getConnection() {
        if (connection == null) {
            connection = new 
        }
    }


    @Override
    public User getUser(String name) {
        return null;
    }

    @Override
    public Set<String> getUserNames() {
        return null;
    }

    @Override
    public Set<Account> getAccounts(User owner) {
        return null;
    }

    @Override
    public Set<Record> getRecords(Account account) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addAccount(User user, Account account) {

    }

    @Override
    public void addRecord(Account account, Record record) {

    }

    @Override
    public User removeUser(String name) {
        return null;
    }

    @Override
    public Account removeAccount(User owner, Account account) {
        return null;
    }

    @Override
    public Record removeRecord(Account from, Record record) {
        return null;
    }
}
