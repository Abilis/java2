package ru.java2.lesson2_4;

import java.sql.*;
import java.sql.Date;
import java.util.*;


/**
 * Created by Abilis on 11.04.2016.
 */
public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/mesOfUsers";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static void main(String[] args) {

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)
            )

            {

                //запрос выводит имена пользователей, на которых подписан Mike
                String query1 = "SELECT `followers` FROM `messages` WHERE username=\"Mike\";";

                //Запрос выводит количество зарегистрированных пользователей
                String query2 = "SELECT COUNT(*) FROM `messages`;";


                statement = connection.createStatement();

                //1 запрос
                resultSet = statement.executeQuery(query1);

                while (resultSet.next()) {
                    String followers = resultSet.getString("followers");
                    System.out.println("Подписчики Майка: " + followers);
                }

                //2 запрос
                resultSet = statement.executeQuery(query2);

                while (resultSet.next()) {
                    String amountUsers = resultSet.getString("COUNT(*)");
                    System.out.println("Количество зарегистрированных пользователей: " + amountUsers);
                }

                System.out.println();
                //3 запрос
                //Запрос выводит все сообщения от тех пользователей, на которых подписан Mike. Сообщения должны быть
                //отсортированы по дате. На выходе нужны сообщение - дата

                //Пойдем более хитрым путем. Сначала вытащим всех пользователей, на которых подписан Mike.
                //Эти данные уже можно вытащить первым запросом
                resultSet = statement.executeQuery(query1);

                String followersMike = "";
                while (resultSet.next()) {
                    followersMike = resultSet.getString("followers");
                }

                //Мы знаем, что подписчики в БД разделены запятой с пробелом
                String[] followersMikeAsArr = followersMike.split(", ");

                //Теперь для каждого подписчика Майка мы можем вытащить строку сообщение - дата
                //Формируем запрос
                String query3 = "SELECT `message`, `dt` FROM `messages` WHERE";

                for (int i = 0; i < followersMikeAsArr.length; i++) {

                    query3 += " username=\""+ followersMikeAsArr[i] + "\" OR";

                }

                //Отрезаем лишние три символа на конце
                query3 = query3.substring(0, query3.length() - 3);

                //Добавляем в конец сортировку по дате
                query3 +=" ORDER BY `dt`;";

                //Теперь запрос можно выполнить
                resultSet = statement.executeQuery(query3);

                while (resultSet.next()) {
                    String message = resultSet.getString("message");
                    Date date = resultSet.getDate("dt");

                    System.out.println(message + " " + date);
                }

                System.out.println();

                //4 запрос
                //Запрос выводит количество подписчиков для каждого пользователя, отсортировано по убыванию.
                //На выходе логин пользователя - количество подписчиков

                String query4 = "SELECT `username`, `followers` FROM `messages`;";
                //эта штука вытаскивает всех пользователей со всеми его подписчиками

                ArrayList<String> listOfUsers = new ArrayList<String>();
                ArrayList<Integer> listOfAmountFollowers = new ArrayList<Integer>();


                //выполняем запрос и обрабатываем
                resultSet = statement.executeQuery(query4);

                while (resultSet.next()) {

                    String username = resultSet.getString("username");
                    String followers = resultSet.getString("followers");

                    listOfUsers.add(username);
                    String[] followersAsArr = followers.split(", ");
                    int amountOfFollowers = followersAsArr.length;

                    listOfAmountFollowers.add(amountOfFollowers);
                }

                //теперь будем сортировать оба списка по числу подписчиков по убыванию числа подписчиков

                for (int i = 0; i < listOfAmountFollowers.size(); i++) {

                    for (int j = 0; j < listOfAmountFollowers.size() - i - 1; j++) {
                        if (listOfAmountFollowers.get(j + 1) > listOfAmountFollowers.get(j)) {
                            int tmp = listOfAmountFollowers.get(j);
                            listOfAmountFollowers.set(j, listOfAmountFollowers.get(j + 1));
                            listOfAmountFollowers.set(j + 1, tmp);

                            String usernameTmp = listOfUsers.get(j);
                            listOfUsers.set(j, listOfUsers.get(j + 1));
                            listOfUsers.set(j + 1, usernameTmp);
                        }
                    }
                }


                for (int i = 0; i < listOfUsers.size(); i++) {
                    System.out.println(listOfUsers.get(i) + " " + listOfAmountFollowers.get(i));
                }


        } catch (SQLException e) {
            System.out.println("Что-то не получилось");
            e.printStackTrace();
        }


    }




}
