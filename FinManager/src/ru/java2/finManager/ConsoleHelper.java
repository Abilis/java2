package ru.java2.finManager;

import ru.java2.finManager.exceptions.ExitException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Abilis on 12.04.2016.
 */
public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));



    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String str = reader.readLine();
        return str;
    }

    //заготовка под метод для чтения пароля с консоли
    public static String readPassword() {

        return null;
    }


    public static int getNumber(int min, int max, String message) throws ExitException {

        int number;

        while (true) {
            try {
                writeMessage(message);

                String sNumber = readString();

                if (sNumber.equalsIgnoreCase("exit")) {
                    throw new ExitException();
                }

                number = Integer.parseInt(sNumber);

                if (number < min || number > max) {
                    throw new IOException();
                }
                else {
                    break;
                }
            } catch (IOException | NumberFormatException e) {
                writeMessage("Некорректные данные");
            }
        }

        return number;
    }


    //метод возвращает массив строк из двух элементов. Первый элемент - логин, второй - пароль
    public static String[] getValidateLoginAndPassword() {

        String[] validLoginAndPass = new String[2];

        while (true) {
            try {

                writeMessage("Введите логин и пароль:");

                String login = readString();
                String password = readString();

                login = login.trim();
                password = password.trim();

                if (login.length() != 0 && password.length() != 0) {
                    validLoginAndPass[0] = login;
                    validLoginAndPass[1] = password;
                    break;
                }

            } catch (IOException e) {
                writeMessage("Некорректные данные, попробуйте снова");
            }
        }

        return validLoginAndPass;
    }

    //в этом методе пользователь вводит новую сумму для новой записи
    public static int getSumForNewRecord() throws ExitException {

        while (true) {
            writeMessage("Введите сумму:");
            try {
                String sSum = readString();

                if (sSum.equalsIgnoreCase("exit")) {
                    throw new ExitException();
                }

                int sum = Integer.parseInt(sSum);

                if (sum <= 0) {
                    throw new IOException();
                }

                return sum;

            } catch (IOException e) {
                writeMessage("Некорректные данные. Попробуйте еще раз");
            }
        }
    }

    //в этом методе пользователь вводит описание для новой записи
    public static String getDescriptionForNewRecord() throws ExitException {

        while (true) {

            writeMessage("Введите описание записи:");

            try {
                String description = readString();

                if (description.equalsIgnoreCase("exit")) {
                    throw new ExitException();
                }

                if (description.length() == 0) {
                    throw new IOException();
                }
                return description;

            } catch (IOException e) {
                writeMessage("Некорректные данные. Попробуйте еще раз");
            }

        }
    }

    //в этом методе пользователь вводит категорию для новой записи
    public static String getCategoryForNewRecord() throws ExitException {

        while (true) {

            writeMessage("Введите категорию записи:");

            try {
                String category = readString();

                if (category.equalsIgnoreCase("exit")) {
                    throw new ExitException();
                }

                if (category.length() == 0) {
                    throw new IOException();
                }
                return category;

            } catch (IOException e) {
                writeMessage("Некорректные данные. Попробуйте еще раз");
            }

        }
    }

    //в этом методе пользователь ставит метку для новой записи (0 - снятие, 1 - пополнение)
    public static int getLabelForNewRecord() throws ExitException {

        while (true) {


            int label = getNumber(0, 1, "Введите метку: 0 - снятие, 1 - пополнение");

            return label;
        }
    }

    //метод печатает первые 10 записей в списке. Затем предлагает напечатать следующие 10 записей, если записи еще есть
    //Если есть предыдущие записи - предлагается напечатать их. Также предлагается выйти из метода
    public static void printTenRecords(List list) {

        int lengthSelection = 3; //длина выборки. На период выборки пусть будет по 3 записи
        String inputStr = "";

        int firstIndex = 0; //первый индекс в списке
        int lastIndex = firstIndex + lengthSelection - 1; //последний индекс записи в списке для текущей выдачи

        //проверка на случай, если длина списка окажется меньше последнего индекса
        if (lastIndex > list.size() - 1) {
            lastIndex = list.size() - 1;
        }

        while (true) {

            //распечатываем список по индексам
            for (int i = firstIndex; i <= lastIndex; i++) {
                writeMessage((i + 1) + ": " + list.get(i).toString());
            }


            writeMessage("1 - новее, 2 - старее, exit - закончить просмотр");

            try {
                inputStr = readString();
            } catch (IOException e) {
                writeMessage("Непонятная ошибка");
            }


            if (inputStr.equalsIgnoreCase("exit")) { //выходим из метода
                break;
            }

            //показать более новые записи
            if (inputStr.equals("1")) {

                //смещаем индексы "влево"
                firstIndex -= lengthSelection;
                lastIndex -= lengthSelection;

                //Если начальный индекс стал меньше 0, то присваиваем ему 0
                if (firstIndex < 0) {
                    firstIndex = 0;
                    //при этом конечный индекс должен стать больше на lenghtSelection - 1 больше начального
                    lastIndex = firstIndex + lengthSelection - 1;

                    //но конечный индекс мог стать больше длины списка. В этом случае присваиваем ему длина_списка - 1
                    if (lastIndex > list.size() - 1) {
                        lastIndex = list.size() - 1;
                    }
                }

                //если начальный индекс не меньше 0, то конечный индекс также заведомо не меньше 0

            }

            //показать более старые записи
            else if (inputStr.equals("2")) {

                //смещаем индексы "вправо"
                firstIndex += lengthSelection;
                lastIndex += lengthSelection;

                //Если конечный индекс стал больше размера списка, то присваиваем ему длина_списка - 1
                if (lastIndex > list.size() - 1) {
                    lastIndex = list.size() - 1;
                    //при этом начальный индекс должен стать на lenghtSeceltion + 1 меньше конечного списка
                    firstIndex = lastIndex - lengthSelection + 1;

                    //но начальный индекс мог стать меньше 0. В таком случае присваиваем ему 0
                    if (firstIndex < 0) {
                        firstIndex = 0;
                    }
                }

                //если конечный индекс не больше размера списка, то начальный также заведом не больше размера списка

            }






        } //конец цикла
    }

}
