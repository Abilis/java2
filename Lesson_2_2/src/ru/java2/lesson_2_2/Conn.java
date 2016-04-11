package ru.java2.lesson_2_2;

import java.sql.*;

/**
 * Created by Abilis on 11.04.2016.
 */
public class Conn {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;


    //подключение к БД
    public static void conn() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:db-test.s3db");

        System.out.println("База подключена!");
    }

    //создание таблица
    public static void createDb() throws SQLException {
        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'password' text);");

        System.out.println("Таблица создана или уже существует");
    }

    //заполнение таблица
    public static void writeDb() throws SQLException {
        statement.execute("INSERT INTO 'users' ('name', 'password') VALUES ('Ivan', '12345') ");
        statement.execute("INSERT INTO 'users' ('name', 'password') VALUES ('Petr', '123') ");
        statement.execute("INSERT INTO 'users' ('name', 'password') VALUES ('Alice', '777') ");
        statement.execute("INSERT INTO 'users' ('name', 'password') VALUES ('Mobs', '1897') ");


        System.out.println("Таблица заполнена");
    }

    //вывод таблица
    public static void readDb() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM users");

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");

            System.out.println("ID = " + id);
            System.out.println("name = " + name);
            System.out.println("password = " + password);
            System.out.println();

        }

    }


    //закрытие
    public static void closeDb() throws SQLException {
        connection.close();
//        statement.close();
//        resultSet.close();
    }

}
