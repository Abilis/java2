package ru.java2.lesson6_2;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Abilis on 11.05.2016.
 */
public class Util {

    private Util(){

    }

    public static void cloceQutely(Closeable resource) {

        try {
            resource.close();
        } catch (IOException ignore) {
            /*NOP*/
        }
    }

}
