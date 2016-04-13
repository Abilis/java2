package ru.java2.finManager;

import ru.java2.finManager.exceptions.ExitException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
