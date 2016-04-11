package ru.java2.lesson2_3;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Abilis on 11.04.2016.
 */
public class Main {

    private static final String URL = "jdbc:mysql://localhost:3307/mydbtest?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        Connection connection;

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }

            connection.close();

            if (connection.isClosed()) {
                System.out.println("Соединение с БД закрыто");
            }

        } catch (SQLException e) {
            System.err.println("Не удалось загрузить класс драйвера!");
        }



    }
}
