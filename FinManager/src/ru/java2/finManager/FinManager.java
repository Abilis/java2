package ru.java2.finManager;


import java.util.ArrayList;

/**
 * Created by Abilis on 12.04.2016.
 */
public class FinManager {

    private static User currentUser;
    private static ArrayList<Account> accountsOfCurrentUser;

    public static void main(String[] args) {

        //авторизация пользователя (либо создание нового аккаунта
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
        } // конец цикла авторизации

        //если пользователь дошел сюда, значит он есть в системе

        //здесь будет основной цикл. Пользователю предлагают выбрать текущий аккаунт.
        //Возможные варианты действий:
        //1 - выбор аккаунта из списка
        //2 выход из программы


        //внутри аккаунта будет еще цикл. Возможные варианты действий:
        //1 - просмотр всех записей
        //2 - добавить новую запись
        //3 - выход из аккаунта. При этом пользователь вовращается в меню выбора аккаунта



  /*      //вытаскиваем из БД аккаунты текущего пользователя и просим выбрать конкретный
        accountsOfCurrentUser = currentUser.getAccounts();


        ConsoleHelper.writeMessage("Ваши аккаунты, " + currentUser.getLogin() + ":");
        ConsoleHelper.writeMessage(accountsOfCurrentUser.toString());
        int inputNum = ConsoleHelper.getNumber(1, accountsOfCurrentUser.size(), "Выберите аккаунт (1 - " + accountsOfCurrentUser.size() + "):");


        //для выбранного аккаунта вытаскиваем все его записи и помещаем в поле listOfRecords типа Record

        ConsoleHelper.writeMessage(accountsOfCurrentUser.get(inputNum - 1).toString());

        ConsoleHelper.writeMessage(accountsOfCurrentUser.get(inputNum - 1).getListOfRecords().toString());

        DbHelper dbHelper = DbHelper.getDbHelper();
        boolean result = dbHelper.addRecord(1, 999, "Вставленное описание", "Вставленная категория", 1);

        System.out.println(result);*/

    }

}
