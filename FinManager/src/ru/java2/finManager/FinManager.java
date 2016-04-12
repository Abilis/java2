package ru.java2.finManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Abilis on 12.04.2016.
 */
public class FinManager {

    private static User currentUser;

    public static void main(String[] args) {

  /*      while (true) {

            int number = ConsoleHelper.getNumber(1, 2, "1 - авторизация, 2 - регистрация нового пользователя");

            if (number == 1) {
                String[] loginAndPass = ConsoleHelper.getValidateLoginAndPassword();
                currentUser = new User(loginAndPass[0], loginAndPass[1]);
                if (currentUser.autorization()) {
                    break;
                }
            }
            else if (number == 2) {

                String[] loginAndPass = ConsoleHelper.getValidateLoginAndPassword();
                currentUser = new User(loginAndPass[0], loginAndPass[1]);

                if (currentUser.registrationNewUser()) {
                    break;
                }
            }
        } // конец цикла*/

        //если пользователь дошел сюда, значит он есть в системе

        DbHelper dbHelper = DbHelper.getDbHelper();
        String query = "SELECT * FROM `users`;";

        try (Connection connection = dbHelper.getConnection())

            {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("password");

                ConsoleHelper.writeMessage(id + " " + login + " " + pass);

            }

        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Что-то не получилось");
            e.printStackTrace();

        }
    }

}
