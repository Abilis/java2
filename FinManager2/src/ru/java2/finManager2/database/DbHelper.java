package ru.java2.finManager2.database;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Category;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;
import ru.java2.finManager2.exceptions.*;
import ru.java2.finManager2.utils.DateFormatForMySql;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Abilis on 19.04.2016.
 */
public class DbHelper implements DataStore {

    private static DbHelper dbHelper = null;
    private Connection connection = null;

    //Параметры подключения к MySQL
    private final String URL = "jdbc:mysql://localhost:3306/finmanager?autoReconnect=true&userSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    //Параметры подключения к sqlite
//    private final String URL = "jdbc:sqlite://localhost:3306/finmanager?autoReconnect=true&userSSL=false";
//    private final String USERNAME = "";
//    private final String PASSWORD = "";

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
            String query = "SELECT * FROM `records` WHERE `id_acc`=\"" + account.getIdAcc() + "\" ORDER BY `id_rec` DESC;";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idRecord = resultSet.getInt("id_rec");
                boolean label = resultSet.getBoolean("label");
                String dt = resultSet.getString("dt");
                Date date = DateFormatForMySql.getDateFormatFromMySql(dt);

                int sum = resultSet.getInt("sum");
                String descriprion = resultSet.getString("description");

                String categoryStr = resultSet.getString("category");
                Category category = Category.OTHER;

                //Определяем категорию транзакции
                Category[] categories = Category.values();

                for (int i = 0; i < categories.length; i++) {
                    if (categoryStr.equals(categories[i].toString())) {
                        category = categories[i];
                    }
                }

                //создаем новую запись и добавлем в список
                result.add(new Record(idRecord, label, date, sum, descriprion, category));

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
    public void addAccount(User user, Account account) throws ExistSuchAccountException, SQLException {



        try (Connection connection = getConnection()) {

            String idUser = "";

            //Вытаскиваем id пользователя
            String queryIdUser = "SELECT `id_user` FROM `users` WHERE `login`=\"" + user.getLogin() + "\";";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryIdUser);

            while (resultSet.next()) {
                idUser = resultSet.getString("id_user");
            }

            //проверяем, есть ли такой аккаунт в БД

            int count = 0;

            String queryGetAccountByName = "SELECT COUNT(*) FROM `accounts` WHERE `description`=\"" + account.getDescription() + "\";";
            resultSet = statement.executeQuery(queryGetAccountByName);


            while (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");
            }

            //если возвращено какое-то число, то это означает, что аккаунт с таким названием уже есть
            if (count != 0) {
                throw new ExistSuchAccountException("Аккаунт с таким описанием уже существует");
            }


            //Формируем запрос на создание нового аккаунта
            String queryNewAcc = "INSERT INTO `accounts` (`description`, `ostatok`, `id_user`) VALUES" +
                    " (\"" + account.getDescription() + "\", \"" + account.getOstatok() + "\", \"" + idUser + "\");";

            //выполняем запрос
            int res = statement.executeUpdate(queryNewAcc);

            if (res != 1) {
                throw new SQLException("Неверный ответ от БД");
            }

        }



    }

    @Override
    public void addRecord(Account account, Record record) throws SQLException, DontAddRecordException, NoEnoughtMoneyException {

        //если метка снятие - проверяем, достаточно ли на аккаунте остатка средств
        if (!record.isLabel()) {
            if (account.getOstatok() < record.getSum()) {
                throw new NoEnoughtMoneyException("Недостаточно средств на аккаунте");
            }
        }

        //ставим метку в 0 или 1
        int label = 0;

        if (record.isLabel()) {
            label = 1;
        }

        //приводим дату в удобный для MySQL
        String dateRecord = DateFormatForMySql.getDateFormatForMySql(record.getDateOfRecord());


        try (Connection connection = getConnection()) {

            //Формируем запрос
            String query = "INSERT INTO `records`(`label`, `dt`, `sum`, `description`, `category`, `id_acc`) " +
                    "VALUES (\"" + label + "\", \"" + dateRecord + "\", \"" +
                    record.getSum() + "\", \"" + record.getDescription() + "\", \"" + record.getCategory().toString() +
                    "\", \"" + account.getIdAcc() + "\");";


            //Выполняем запрос
            Statement statement = connection.createStatement();
            int n = statement.executeUpdate(query);

            if (n == 0) {
                throw new DontAddRecordException("Неверный ответ БД");
            }

            int newSum;

            //если метка пополнения - меняем знак суммы
            if (record.isLabel()) {
                newSum = -record.getSum();
            }
            else {
                newSum = record.getSum();
            }

            //уменьшаем на аккаунте сумму остатка
            newSum = account.getOstatok() - newSum;



            String queryWithdraw = "UPDATE `accounts` SET `ostatok`=\"" + newSum + "\" WHERE id_acc=\""
                    + account.getIdAcc() + "\";";

            statement.executeUpdate(queryWithdraw);
        }

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
    public void removeRecord(Account from, Record record) throws SQLException {

        try (Connection connection = getConnection())
        {
            //формируем запрос
            String query = "DELETE FROM `records` WHERE `id_rec`=\"" + record.getIdRecord() + "\" AND `id_acc`=\"" +
                    from.getIdAcc() + "\";";

            //выполняем запрос
            Statement statement = connection.createStatement();

            int n = statement.executeUpdate(query);

            if (n == 0) {
                throw new SQLException("Не удалось удалить транзакцию с id " + record.getIdRecord());
            }

        }

    }

    @Override
    public void updateRecord(Record record) throws SQLException {

        try (Connection connection = getConnection()) {

            //подготовка данных
            int labelNum = 0;

            if (record.isLabel()) {
                labelNum = 1;
            }

            Date date = record.getDateOfRecord();
            String dateStr = DateFormatForMySql.getDateFormatForMySql(date);


            //формируем запрос
            String query = "UPDATE `records` SET `label`=\"" + labelNum + "\", `dt`=\"" + dateStr +
                    "\", `sum`=\"" + record.getSum() + "\", `description`=\"" + record.getDescription() +
                    "\", `category`=\"" + record.getCategoryAsString() +"\" WHERE `id_rec`=\"" + record.getIdRecord() + "\";";

            //выполняем запрос
            Statement statement = connection.createStatement();

            int n = statement.executeUpdate(query);

            if (n == 0) {
                throw new SQLException("Не удалось обновить транзакцию " + record);
            }

        }

    }
}
