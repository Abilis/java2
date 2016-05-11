package ru.java.lesson6_3.generateFiles;

import java.io.*;

/**
 * Created by Abilis on 11.05.2016.
 */
public class Generator {

    private static final String DIR_NAME = "D:\\temp\\testfile\\testdir";
    private static final int AMOUNT_FILES = 15; //количество файлов
    private static final int LENGHT_STRING = 150; //длина строки в файле до перехода на новую строку
    private static final int AMOUNT_LINES_IN_FILE = 100_000; //общее количество строк в файлах
    private static final String VOCABULARY = " абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ0123456789";

    public static void main(String[] args) {



        for (int i = 1; i <= AMOUNT_FILES; i++) {

            OutputStreamWriter outputStreamWriter = null;
            try {
                File file = new File(DIR_NAME + "\\" + i + ".txt");
                 outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                fillFile(outputStreamWriter);
                System.out.println("Файл " + i + " заполнен случайными строками. Количество строк: " + AMOUNT_LINES_IN_FILE);
            } catch (IOException e) {
                System.out.println("Что-то пошло не так при записи файла " + i + ": " + e.getMessage());
            } finally {
                closeQuitely(outputStreamWriter);
            }

        }


    }

    private static String getRandomString(int lenght) {

        StringBuilder result = new StringBuilder("");

        for (int i = 0; i < lenght; i++) {
            result.append(getRandomSymbol());
        }

        result.append("\n");

        return result.toString();
    }

    private static char getRandomSymbol() {

        int index = (int) (Math.random() * VOCABULARY.length());
        char sym = VOCABULARY.charAt(index);
        return sym;
    }

    private static void fillFile(OutputStreamWriter out) throws IOException {

        for (int i = 0; i < AMOUNT_LINES_IN_FILE; i++) {
            out.write(getRandomString(LENGHT_STRING));
        }

    }

    private static void closeQuitely(Closeable resourse) {

        if (resourse != null) {
            try {
                resourse.close();
            } catch (IOException ignore) {
                /*NOP*/
            }
        }
    }

}
