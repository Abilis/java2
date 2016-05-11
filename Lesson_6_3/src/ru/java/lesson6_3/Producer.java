package ru.java.lesson6_3;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Abilis on 11.05.2016.
 */
public class Producer extends Thread {

    private static ConcurrentLinkedQueue<File> listOfFiles;
    private static int countFiles = 1;

    public Producer(ConcurrentLinkedQueue<File> listOfFiles) {
        this.listOfFiles = listOfFiles;
    }

    @Override
    public void run() {



    }

    private File getFile() {
        if (!listOfFiles.isEmpty()) {
            return listOfFiles.poll();

        }
        else {
            interrupted();
            return null;
        }
    }
}
