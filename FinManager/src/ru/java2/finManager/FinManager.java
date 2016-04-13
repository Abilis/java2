package ru.java2.finManager;


import ru.java2.finManager.exceptions.ErrorWriteInDBExceprion;
import ru.java2.finManager.exceptions.ExitException;

import java.util.ArrayList;


/**
 * Created by Abilis on 12.04.2016.
 */
public class FinManager {

    private static User currentUser;
    private static ArrayList<Account> accountsOfCurrentUser;

    private static int accNum;
    private static int recNum;

    public static void main(String[] args) {

        //авторизация пользователя (либо создание нового аккаунта
        while (true) {

            int number = 0;
            try {
                number = ConsoleHelper.getNumber(1, 2, "1 - авторизация, 2 - регистрация нового пользователя. Exit - выход");
            } catch (ExitException e) {
                System.exit(112);
            }

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
        while (true) {
            //вытаскиваем все аккаунты пользователя
            accountsOfCurrentUser = currentUser.getAccounts();

            ConsoleHelper.writeMessage("Ваши аккаунты, " + currentUser.getLogin() + ":");
            ConsoleHelper.writeMessage(accountsOfCurrentUser.toString());

            if (accountsOfCurrentUser.size() == 0) {
                ConsoleHelper.writeMessage("Вам необходимо создать хотя бы 1 аккаунт");
                DbHelper dbHelper = DbHelper.getDbHelper();
                dbHelper.createNewAccount(currentUser);
                continue;
            }

            try {
                accNum = ConsoleHelper.getNumber(1, accountsOfCurrentUser.size(),
                        "Выберите аккаунт (1 - " + accountsOfCurrentUser.size() + "). Exit - выход:");
            } catch (ExitException e) {
                System.exit(112);
            }

            //внутри аккаунта будет еще цикл. Возможные варианты действий:
            //1 - просмотр всех записей
            //2 - добавить новую запись
            //3 - выход из аккаунта. При этом пользователь вовращается в меню выбора аккаунта
            //4 - создание нового аккаунта

            //для выбранного аккаунта вытаскиваем все его записи и помещаем в поле listOfRecords типа Record

            while (true) {

                //печатаем данные об аккаунте
                ConsoleHelper.writeMessage(accountsOfCurrentUser.get(accNum - 1).toString());

                //печатаем записи, хранящиеся в аккаунте
                ConsoleHelper.writeMessage(accountsOfCurrentUser.get(accNum - 1).getListOfRecords().toString());


                try {
                    recNum = ConsoleHelper.getNumber(1, 2, "1 - добавить новую запись, " +
                            "2 - создать новый аккаунт, exit - выход из аккаунта");

                    if (recNum == 1) {
                        //добавляем запись в БД
                        int idAcc = accountsOfCurrentUser.get(accNum - 1).getIdAccount();

                        DbHelper dbHelper = DbHelper.getDbHelper();

                        int label = ConsoleHelper.getLabelForNewRecord();

                        int sum = ConsoleHelper.getSumForNewRecord();

                        //если введенная сумма больше остатка на аккаунте - говорим об этом
                        if (sum > accountsOfCurrentUser.get(accNum - 1).getOstatok() && label == 0) {
                            ConsoleHelper.writeMessage("Введенная сумма меньше остатка на счете. Пополните счет или выберите меньшую сумму");
                            continue;
                        }

                        String description = ConsoleHelper.getDescriptionForNewRecord();
                        String category = ConsoleHelper.getCategoryForNewRecord();



                        if (!dbHelper.addRecord(idAcc, sum, description, category, label)) {
                            throw new ErrorWriteInDBExceprion();
                        }

                        //если удалось сделать инсерт в БД - обнуляем текущий список запишей для активного аккаунта
                        //чтобы он был запрошен из БД заново
                        accountsOfCurrentUser.get(accNum - 1).setListOfRecords(null);

                        //еще нам нужно изменить сумму остатка на счету
                        dbHelper.changeOstatokOnAcc(idAcc, accountsOfCurrentUser.get(accNum - 1).getOstatok(), sum, label);

                        //и изменяем сумму остатка в поле аккаунта
                        accountsOfCurrentUser.get(accNum - 1).setOstatok(accountsOfCurrentUser.get(accNum - 1).getOstatok() - sum);
                    }
                    else if (recNum == 2) {
                        //создаем новый аккаунт для текущего пользователя
                        DbHelper dbHelper = DbHelper.getDbHelper();
                        dbHelper.createNewAccount(currentUser);
                        break;
                    }




                } catch (ExitException e) {
                    break;
                } catch (ErrorWriteInDBExceprion e) {
                    ConsoleHelper.writeMessage("Не удалось записать данные в БД. Попробуйте еще раз");
                    continue;


                }


            }


        }


    }

}
