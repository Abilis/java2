package ru.java2.lesson5_6.filetransfer;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Abilis on 05.05.2016.
 */
public class Util {

    //метод предназначен для тихого закрытия ресурса
    public static void closeQuite(Closeable resourse) {

        if (resourse != null) {
            try {
                resourse.close();
            } catch (IOException ignore) {
                /*NOP*/
            }
        }

    }

}
