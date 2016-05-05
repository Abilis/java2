package ru.java2.finManager2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Abilis on 05.05.2016.
 */
public class InitSqliteDB {

    //параметры подключения к Sqlite
    private static final String URL = "jdbc:sqlite:finmanager.db";

    private static final String QUERY_CREATE_ACCOUNTS = "CREATE TABLE IF NOT EXISTS accounts (id_acc INTEGER PRIMARY KEY" +
            " AUTOINCREMENT NOT NULL, description STRING, ostatok INTEGER, id_user INTEGER);";

    private static final String QUERY_CREATE_RECORDS = "CREATE TABLE IF NOT EXISTS records (id_rec INTEGER PRIMARY KEY" +
            " AUTOINCREMENT NOT NULL, label INTEGER, dt STRING, sum INTEGER, description STRING, category STRING, id_acc INTEGER);";

    private static final String QUERY_CREATE_USERS = "CREATE TABLE IF NOT EXISTS users (id_user INTEGER PRIMARY KEY" +
            " AUTOINCREMENT NOT NULL, login STRING, password STRING);";


    //метод инициализирует БД при запуске. Если ее нет - создает. Если нет необходимых таблиц - создает и их
    public static void initSqlitedDb() {

        try (Connection connection = DriverManager.getConnection(URL))
        {
            Statement statement = connection.createStatement();
            statement.execute(QUERY_CREATE_ACCOUNTS);
            statement.execute(QUERY_CREATE_RECORDS);
            statement.execute(QUERY_CREATE_USERS);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
