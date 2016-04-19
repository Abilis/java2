package ru.java2.finManager2.database;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;
import ru.java2.finManager2.exceptions.NoSuchUserException;

import java.sql.*;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Abilis on 19.04.2016.
 */
public class DbHelper implements DataStore {

    private static DbHelper dbHelper = null;
    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/finmanager?autoReconnect=true&userSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private DbHelper() {

    }

    public static DbHelper getDbHerper() {
        if (dbHelper == null) {
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }

    public Connection getConnection() throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, properties);
        }

        return connection;
    }

    // return null if no such user
    @Override
    public User getUser(String name) throws SQLException, NoSuchUserException {

        try (Connection connection = getConnection())

        {
            //формируем запрос
            String query = "SELECT * FROM `users` WHERE `login`=\"" + name + "\";";

            Statement statement = connection.createStatement();

            //выполняем запрос
            ResultSet resultSet = statement.executeQuery(query);

            String password = null;

            while (resultSet.next()) {
                password = resultSet.getString("password");
            }

            //если строка пароля оказалась равна null - значит, пользователя с таким логином в базе нет
            if (password == null) {
                throw new NoSuchUserException("Пользователь с таким именем не существует!");
            }

            //создаем нового пользователя на основе вытащенного из БД
            User user = new User(name, password);

            return user;

        }

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
