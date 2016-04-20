package ru.java2.finManager2.database;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;
import ru.java2.finManager2.exceptions.DontCreateNewUserException;
import ru.java2.finManager2.exceptions.ExistSuchUserException;
import ru.java2.finManager2.exceptions.NoSuchUserException;

import java.sql.*;
import java.util.ArrayList;
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
    public ArrayList<Account> getAccounts(User owner) {

        ArrayList<Account> result = new ArrayList<>();

        //формируем запрос
        String query = "SELECT `id_acc`, `ostatok`, `description` FROM `accounts`, `users`" +
                "WHERE `accounts`.`id_user`=`users`.`id_user` AND `users`.`login`=\"" + owner.getLogin() + "\";";

        //Выполняем запрос
        try (Connection connection = getConnection())
        {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            //разбираем то, что вернула база и собираем список аккаунтов


            while (resultSet.next()) {
                String description = resultSet.getString("description");
                int ostatok = Integer.parseInt(resultSet.getString("ostatok"));

                //создаем новый аккаунт по полученным данным и добавляем в список
                result.add(new Account(description, ostatok));
            }

        }


        return result;
    }

    @Override
    public Set<Record> getRecords(Account account) {
        return null;
    }

    @Override
    public void addUser(User user) throws SQLException, ExistSuchUserException {

        //проверяем, есть ли пользователь с таким логином в базе. Если да - кидаем эксепшн
        try {
            User userInDb = getUser(user.getLogin());
            //раз дошли сюда - значит, пользователь с таким логином уже есть
            throw new ExistSuchUserException("Пользователь с таким именем уже существует!");

        } catch (NoSuchUserException Ignored) {
        }

        //если нет - создаем запись в БД

        try (Connection connection = getConnection())
        {
            //формируем запрос
            String query = "INSERT INTO `users` (`login`, `password`) VALUES (\"" + user.getLogin() + "\", \""
                    + user.getPassword() + "\");";

            Statement statement = connection.createStatement();

            //Выполняем запрос
            int n = statement.executeUpdate(query);

            if (n == 0) {
                throw new DontCreateNewUserException("Не удалось создать пользователя с логином " + user.getLogin());
            }
        }

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
