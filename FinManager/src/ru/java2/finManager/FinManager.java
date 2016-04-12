package ru.java2.finManager;

/**
 * Created by Abilis on 12.04.2016.
 */
public class FinManager {


    public static void main(String[] args) {

        while (true) {

            ConsoleHelper.writeMessage("Введите логин и пароль:");
            String[] loginAndPassword = ConsoleHelper.getValidateLoginAndPassword();

            User user = new User(loginAndPassword[0], loginAndPassword[1]);

            if (user.autorization()) {
                ConsoleHelper.writeMessage("Авторизация прошла успешно!");
                break;
            }
            else {
                ConsoleHelper.writeMessage("Неверный логин или пароль!");
            }
        }

        //Теперь пользователь залогинен
        ConsoleHelper.writeMessage("Теперь пользователь залогинен");


    }

}
