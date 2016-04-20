package ru.java2.finManager2;

import java.util.ArrayList;

/**
 * Created by Abilis on 19.04.2016.
 */
public class Account {

    private String description;
    private int ostatok;
    private ArrayList<Record> records;

    public String getDescription() {
        return description;
    }

    public int getOstatok() {
        return ostatok;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }
}
