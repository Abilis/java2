package ru.java2.finManager2.database;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Category;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;
import ru.java2.finManager2.exceptions.DontCreateNewUserException;
import ru.java2.finManager2.exceptions.ExistSuchUserException;
import ru.java2.finManager2.exceptions.NoSuchUserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import static ru.java2.finManager2.Category.HEALTH;

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
    public ArrayList<Account> getAccounts(User owner) throws SQLException {

        ArrayList<Account> result = new ArrayList<>();

        //Выполняем запрос
        try (Connection connection = getConnection())
        {

            //формируем запрос
            String query = "SELECT `id_acc`, `ostatok`, `description` FROM `accounts`, `users`" +
                    "WHERE `accounts`.`id_user`=`users`.`id_user` AND `users`.`login`=\"" + owner.getLogin() + "\";";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            //разбираем то, что вернула база и собираем список аккаунтов


            while (resultSet.next()) {
                int idAcc = resultSet.getInt("id_acc");
                String description = resultSet.getString("description");
                int ostatok = resultSet.getInt("ostatok");

                //

                //создаем новый аккаунт по полученным данным и добавляем в список
                Account account = new Account(idAcc, description, ostatok);

                //добавляем получившийся аккаунт в список
                result.add(account);
            }
        }

        return result;
    }


    @Override
    public ArrayList<Record> getRecords(Account account) throws SQLException {
        ArrayList<Record> result = new ArrayList<Record>();



        //выполняем запрос
        try (Connection connection = getConnection())
        {
            //формируем запрос
            String query = "SELECT * FROM `records` WHERE `id_acc`=\"" + account.getIdAcc() + "\" ORDER BY `dt` DESC;";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idRecord = resultSet.getInt("id_rec");
                boolean label = resultSet.getBoolean("label");
                Date dt = resultSet.getDate("dt");
                int sum = resultSet.getInt("sum");
                String descriprion = resultSet.getString("description");

                String categoryStr = resultSet.getString("category");
                Category category = Category.OTHER;

                if (categoryStr.equalsIgnoreCase("HEALTH")) {
                    category = Category.HEALTH;
                }
                else if (categoryStr.equalsIgnoreCase("FOOD")) {
                    category = Category.FOOD;
                }
                else if (categoryStr.equalsIgnoreCase("CLOTHES")) {
                    category = Category.CLOTHES;
                }
                else if (categoryStr.equalsIgnoreCase("TRAVELLING")) {
                    category = Category.TRAVELLING;
                }


                //создаем новую запись и добавлем в список
                result.add(new Record(idRecord, label, dt, sum, descriprion, category));

            }

        }

        return result;
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
