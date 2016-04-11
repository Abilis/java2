package ru.java2.lesson2_3;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Abilis on 11.04.2016.
 */
public class Main {

    private static final String URL = "jdbc:mysql://localhost:3307/mydbtest?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);


        } catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера");
        }


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
            )

            {


                String query1 = "INSERT INTO users (name, age, mail) VALUES ('John', 38, 'john@mail.ru');";
                String query2 = "DELETE FROM users WHERE id=3";
                String query3 = "UPDATE users set age=39, mail='john22@gmail.ru' WHERE id=4;";

                int n = statement.executeUpdate(query3);
                System.out.println(n + " строк изменено");



        } catch (SQLException e) {

        }


    }
}
