package ru.java.lesson6_3;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Abilis on 11.05.2016.
 */
public class Util {

    public static void closeQuitely(Closeable resourse) {

        if (resourse != null) {

            try {
                resourse.close();
            } catch (IOException ignore) {
                /*NOP*/
            }
        }
    }
}
