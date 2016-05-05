package ru.java2.finManager2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Abilis on 22.04.2016.
 */
public class InitDb {

    //Параметры подключения к MySQL
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";



    private static final String QUERY_CREATE_DB = "CREATE DATABASE IF NOT EXISTS `finmanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;";
    private static final String QUERY_USE_DB = "USE `finmanager`;";

    private static final String QUERY_CREATE_ACCOUNTS_TABLE = "CREATE TABLE IF NOT EXISTS `accounts` (\n" +
            "  `id_acc` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `description` varchar(50) NOT NULL,\n" +
            "  `ostatok` int(11) NOT NULL,\n" +
            "  `id_user` int(11) NOT NULL,\n" +
            "  PRIMARY KEY (`id_acc`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";

    private static final String QUERY_CREATE_RECORDS_TABLE = "CREATE TABLE IF NOT EXISTS `records` (\n" +
            "  `id_rec` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `label` tinyint(1) NOT NULL,\n" +
            "  `dt` varchar(30) NOT NULL,\n" +
            "  `sum` int(11) NOT NULL,\n" +
            "  `description` varchar(150) NOT NULL,\n" +
            "  `category` varchar(100) NOT NULL,\n" +
            "  `id_acc` int(11) NOT NULL,\n" +
            "  PRIMARY KEY (`id_rec`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";

    private static final String QUERY_CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS `users` (\n" +
            "  `id_user` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `login` varchar(25) NOT NULL,\n" +
            "  `password` varchar(32) NOT NULL,\n" +
            "  PRIMARY KEY (`id_user`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";

    //метод инициализирует БД при запуске. Если ее нет - создает. Если нет необходимых таблиц - создает и их
    public static void initDb() {

        try (Connection connection = getConnection()) {

            Statement statement = connection.createStatement();

            //выполняем запросы создания БД и таблиц
            statement.execute(QUERY_CREATE_DB);
            statement.execute(QUERY_USE_DB);
            statement.execute(QUERY_CREATE_ACCOUNTS_TABLE);
            statement.execute(QUERY_CREATE_RECORDS_TABLE);
            statement.execute(QUERY_CREATE_USERS_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");

        Connection connection = DriverManager.getConnection(URL, properties);

        return connection;
    }

}
