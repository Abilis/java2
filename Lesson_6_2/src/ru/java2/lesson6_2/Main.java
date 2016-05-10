package ru.java2.lesson6_2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Main {

    private static final String FILE_NAME = "D:\\Temp\\1984.TXT";

    public static void main(String[] args) throws FileNotFoundException {

        InputStream inputStream = new BufferedInputStream(new FileInputStream(FILE_NAME));

    }

}
