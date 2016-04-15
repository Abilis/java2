package ru.java2.finManager;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;


/**
 * Created by Abilis on 12.04.2016.
 */
public class DbHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/finmanager?autoReconnect=true&userSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static DbHelper dbHelper = null;
    private Connection connection = null;

    private DbHelper() {

    }

    public static DbHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DbHelper();
        }

        return dbHelper;
    }


    public Connection getConnection() {

        try {
            Properties properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "utf8");

            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, properties);
            }
            return connection;
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Не удалось подключиться к БД или какая-то еще ошибка с БД");

        }

        return connection;
    }

    //метод проверяет, есть ли в БД в таблице users пользователь с именем login
    public boolean isUser(String login) throws SQLException {

        //Запрос возвращает количество пользователей с логином login
        String query = "SELECT COUNT(*) FROM `users` WHERE login=\"" + login + "\";";

        connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        int count = 0;

        while (result.next()) {
            count = result.getInt("COUNT(*)");
        }

        if (count != 0) {
            connection.close();
            return true;
        }

        connection.close();
        return false;
    }

    //метод добавляет в таблицу users пользователя с именем login и паролем password
    public void createUser(String login, String password) throws SQLException {

        //преобразуем пароль в строку хэш md5
        password = Utils.getMd5(password);

        //запрос добавляет в таблицу users нового пользователя с логином login и паролем password
        String query = "INSERT INTO `users` (login, password) VALUES (\"" + login + "\", \"" + password + "\");";

        connection = getConnection();
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);

        connection.close();

        if (result == 0) {
            throw new SQLException("Не удалось создать пользователя с логином " + login + ".");
        }
    }

    //метод определяет, есть ли в таблице users пользователь с логином login и паролем password
    public boolean checkUser(String login, String password) throws SQLException {

        //Преобразуем пароль в строку хэш-md5
        password = Utils.getMd5(password);

        //Запрос вытаскивает из таблицы users пользователя с переданными логином и паролем
        String query = "SELECT COUNT(*) FROM `users` WHERE `login`=\"" + login + "\" AND `password`=\"" + password + "\";";

        connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(query);

        int amountRows = 0;

        while (result.next()) {
            amountRows = result.getInt("COUNT(*)");
        }

        connection.close();

        if (amountRows == 0) {
            return false;
        }
        else {
            return true;
        }

    }

    //метод дает id_user по логину
    public int getIdUser(String login) throws SQLException {

        //Запрос
        String query = "SELECT `id_user` FROM `users` WHERE `login`=\"" + login + "\";";

        //выполняем запрос
        connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        int idUser = 0;

        while (resultSet.next()) {
            idUser = resultSet.getInt("id_user");
        }

        connection.close();
        return idUser;
    }

    //метод возвращает список аккаунтов для пользователя с id idUser
    public ArrayList<Account> getAccounts(int idUser) throws SQLException {

        ArrayList<Account> accountsUsers = new ArrayList<Account>();

        //запрос
        String query = "SELECT * FROM `accounts` WHERE `id_user`=\"" + idUser + "\";";

        //выполняем запрос
        connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        int numOfAccount = 0;

        while (resultSet.next()) {
            int idAcc = resultSet.getInt("id_acc");
            String accDescription = resultSet.getString("description");
            int ostatok = resultSet.getInt("ostatok");

            //создаем по вытащенным данным новый аккаунт
            Account account = new Account(idAcc, accDescription, ostatok, idUser, ++numOfAccount);

            //добавляем его в список
            accountsUsers.add(account);
        }

        connection.close();

        return accountsUsers;
    }

    //метод вытаскивает список records для аккаунта с id idAcc
    public ArrayList<Record> getRecords(int idAcc) throws SQLException {
        ArrayList<Record> listOfRecords = new ArrayList<Record>();

        //запрос
        String query = "SELECT * FROM `records` WHERE `id_acc`=\"" + idAcc + "\" ORDER BY `dt` DESC;";

        //выполняем запрос
        connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        //разбираем полученные данные и добавляем в список записей
        while (resultSet.next()) {
            int label = resultSet.getInt("label");
            Date dt = resultSet.getDate("dt");
            int sum = resultSet.getInt("sum");
            String description = resultSet.getString("description");
            String category = resultSet.getString("category");

            //Создаем запись на основе полученных данных
            Record record = new Record(label, dt, sum, description, category);

            //добавляем ее в список записей
            listOfRecords.add(record);
        }

        connection.close();
        return listOfRecords;
    }

    //метод добавляет запись в таблице records
    public boolean addRecord(int idAcc, int sum, String description, String category, int label) {

        //устанавливаем текущую дату

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sDate = simpleDateFormat.format(new Date());
        String currentDate = sDate.toString();

        //формируем запрос
        String query = "INSERT INTO `records` (label, dt, sum, description, category, id_acc) VALUES (\"" +
                label + "\", \"" + currentDate + "\", \"" + sum + "\", \"" + description + "\", \"" +
                category + "\", \"" + idAcc + "\");";


        //Выполняем запрос

        try (Connection connection = getConnection())
            {

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {

            return false;
        }

        return true;
    }


    //метод изменяет сумму остатка на аккаунте
    public void changeOstatokOnAcc(int idAcc, int ostatok, int sum, int label) {

        int newOstatok = ostatok + sum;

        //формируем запрос
        String query = "UPDATE `accounts` SET `ostatok`=" + newOstatok + " WHERE `id_acc`=" + idAcc + ";";

        //выполняем запрос
        try (Connection connection = getConnection())
          {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Не удалось изменить сумму на счете. Сделайте это вручную позже");
        }

    }

    //метод создает новый аккаунт для текущего пользователя
    public void createNewAccount(User currentUser) {

        int idUser = currentUser.getIdUser();

        while (true) {

            try (Connection connection = getConnection())
                {
                ConsoleHelper.writeMessage("Введите описание аккаунта. Exit - выход");
                String description = ConsoleHelper.readString();

                if (description.equalsIgnoreCase("exit")) {
                    return;
                }

                if (description.length() == 0) {
                    throw new IOException();
                }

                ConsoleHelper.writeMessage("Введите начальное количество денег на аккаунте. Exit - выход");
                String sOstatok = ConsoleHelper.readString();
                if (sOstatok.equalsIgnoreCase("exit")) {
                    return;
                }

                int ostatok = Integer.parseInt(sOstatok);

                //Формируем запрос
                String query = "INSERT INTO `accounts` (`description`, `ostatok`, `id_user`) VALUES (\"" +
                        description + "\", " + ostatok + ", " + idUser + ");";

                //выполняем запрос
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                return;


            } catch (IOException | NumberFormatException e) {
                ConsoleHelper.writeMessage("Некорректные данные. Попробуйте снова");
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Ошибка при работе с БД");
                return;
            }
        }
    }
}
