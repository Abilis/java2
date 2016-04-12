package ru.java2.finManager;

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

    public static int getNumber(int min, int max, String message) {

        int number;

        while (true) {
            try {
                writeMessage(message);
                number = Integer.parseInt(readString());

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

}
