package ru.java2.lesson2_4;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Abilis on 11.04.2016.
 */
public class DbHelper {

    private final String URL = "jdbc:mysql://localhost:3306/mesOfUsers?autoReconnect=true&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private static Connection connection = null;
    private static DbHelper dbHelper = null;


    private DbHelper() {
    }


    public static final DbHelper getDbHelper() {

        if (dbHelper != null) {
            return dbHelper;
        }
        else {
            dbHelper = new DbHelper();
            return dbHelper;
        }
    }

    public final Connection getConnection() throws SQLException {


        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


        return connection;
    }

}
