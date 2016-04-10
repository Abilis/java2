package ru.java2.lesson_2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Abilis on 10.04.2016.
 */
public class DerbyConnection {

    private final String url = "jdbc:derby:";
    private final String host = "localhost";
    private final String port ="3306";
    private final String user = "test";
    private final String password = "test";


    public Connection getConnection() throws SQLException {
         return DriverManager.getConnection(url+host+":"+port+"?createDb=true", user, password);
    }

}
