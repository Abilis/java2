package ru.java.lesson6_3;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Abilis on 11.05.2016.
 */
public class Main {

    private static final String DIR_NAME = "D:\\temp\\testfile\\testdir";


    public static void main(String[] args) {

        File dir = new File(DIR_NAME);

        File[] files = dir.listFiles();

        ConcurrentLinkedQueue<File> listOfFiles = new ConcurrentLinkedQueue<>();
        listOfFiles.addAll(Arrays.asList(files));




    }
}
