package ru.java2.finManager;

import java.sql.*;

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
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Не удалось подключиться к БД или какая-то еще ошибка с БД");
         //   e.printStackTrace();
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


}
