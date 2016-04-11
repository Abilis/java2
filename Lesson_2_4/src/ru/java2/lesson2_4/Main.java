package ru.java2.lesson2_4;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Abilis on 11.04.2016.
 */
public class Main {

    private static Connection connection;

    public static void main(String[] args) {

        DbHelper dbHelper = DbHelper.getDbHelper();

        try {
            connection = dbHelper.getConnection();
        } catch (SQLException e) {
            System.out.println("Не получилось подключиться к БД");
        }

        try {

            Statement statement = connection.createStatement();

            String query1 = "";
            statement.execute(query1);
        } catch (SQLException e) {
            System.out.println("Не получилось выполнить запрос");
        }
        


    }




}
