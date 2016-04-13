package ru.java2.finManager;


import java.util.ArrayList;

/**
 * Created by Abilis on 12.04.2016.
 */
public class FinManager {

    private static User currentUser;
    private static ArrayList<Account> accountsOfCurrentUser;

    public static void main(String[] args) {

        while (true) {

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
        } // конец цикла

        //если пользователь дошел сюда, значит он есть в системе


        //вытаскиваем из БД аккаунты текущего пользователя и просим выбрать конкретный
        accountsOfCurrentUser = currentUser.getAccounts();

        ConsoleHelper.writeMessage("Ваши аккаунты, " + currentUser.getLogin() + ":");
        ConsoleHelper.writeMessage(accountsOfCurrentUser.toString());


        //для выбранного аккаунта вытаскиваем все его записи и помещаем в поле listOfRecords типа Record
        accountsOfCurrentUser.get(0).getListOfRecords();

        ConsoleHelper.writeMessage(accountsOfCurrentUser.toString());


    }

}
