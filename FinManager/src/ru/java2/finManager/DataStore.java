package ru.java2.finManager;

import java.util.Set;

/**
 * Created by Abilis on 04.04.2016.
 */
public interface DataStore {


    // return null if no such user
    User getUser(String name);


    // If no users, return empty collection (not null)
    Set<String> getUserNames();


    // If no accounts, return empty collection (not null)
    Set<Account> getAccounts(User owner);


    // If no records, return empty collection (not null)
    Set<Record> getRecords(Account account);

    void addUser(User user);

    void addAccount(User user, Account account);

    void addRecord(Account account, Record record);

    // return removed User or null if no such user
    User removeUser(String name);


    // return null if no such account
    Account removeAccount(User owner, Account account);


    // return null if no such record
    Record removeRecord(Account from, Record record);
}
