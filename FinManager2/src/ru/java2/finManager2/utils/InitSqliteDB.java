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

    private static final String QUERY_CREATE_ACCOUNTS = "CREATE TABLE IF NOT EXISTS accounts (\n" +
            "    id_acc      INTEGER (11, 11) PRIMARY KEY\n" +
            "                                 UNIQUE,\n" +
            "    description TEXT,\n" +
            "    ostatok     INTEGER (11, 11),\n" +
            "    id_user     INTEGER (11, 11) \n" +
            ");";

    private static final String QUERY_CREATE_RECORDS = "CREATE TABLE IF NOT EXISTS records (\n" +
            "    id_rec      INTEGER (11, 11) PRIMARY KEY\n" +
            "                                 UNIQUE,\n" +
            "    label       INTEGER (1),\n" +
            "    df          TEXT (30, 30),\n" +
            "    sum         INTEGER (11, 11),\n" +
            "    description TEXT,\n" +
            "    category    TEXT,\n" +
            "    id_acc      INTEGER (11, 11) \n" +
            ");";

    private static final String QUERY_CREATE_USERS = "CREATE TABLE IF NOT EXISTS users (\n" +
            "    id_user  INTEGER (11, 11) PRIMARY KEY\n" +
            "                              UNIQUE,\n" +
            "    login    TEXT (25, 25),\n" +
            "    password TEXT (32, 32) \n" +
            ");";


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
