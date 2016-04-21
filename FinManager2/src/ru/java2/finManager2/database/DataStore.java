package ru.java2.finManager2.database;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;
import ru.java2.finManager2.exceptions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Abilis on 19.04.2016.
 */
public interface DataStore {

    // return null if no such user
    User getUser(String name) throws SQLException, NoSuchUserException;

    // If no users, return empty collection (not null)
    Set<String> getUserNames();

    // If no accounts, return empty collection (not null)
    ArrayList<Account> getAccounts(User owner) throws SQLException;


    // If no records, return empty collection (not null)
    ArrayList<Record> getRecords(Account account) throws SQLException;

    void addUser(User user) throws SQLException, ExistSuchUserException;

    void addAccount(User user, Account account) throws ExistSuchAccountException, SQLException;

    void addRecord(Account account, Record record) throws SQLException, DontAddRecordException, NoEnoughtMoneyException;

    // return removed User or null if no such user
    User removeUser(String name);

    // return null if no such account
    Account removeAccount(User owner, Account account);

    // return null if no such record
    void removeRecord(Account from, Record record) throws SQLException;

    void updateRecord(Account account, Record record) throws SQLException;

}
