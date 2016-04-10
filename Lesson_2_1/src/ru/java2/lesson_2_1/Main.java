package ru.java2.lesson_2_1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Abilis on 10.04.2016.
 */
public class Main {

    public static void main(String[] args) {

        DerbyConnection derbyConnection = new DerbyConnection();

        try (Connection conn = derbyConnection.getConnection())
        {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM Employee");



        } catch (SQLException e) {

        }

    }


}
