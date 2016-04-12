package ru.java2.finManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

}
