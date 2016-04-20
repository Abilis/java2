package ru.java2.finManager2;

import ru.java2.finManager2.database.DbHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Abilis on 19.04.2016.
 */
public class Account {

    private int idAcc;

    public int getIdAcc() {
        return idAcc;
    }

    private String description;
    private int ostatok;
    private ArrayList<Record> records = new ArrayList<Record>();;

    public String getDescription() {
        return description;
    }

    public int getOstatok() {
        return ostatok;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }


    public Account(int idAcc, String description, int ostatok) {
        this.idAcc = idAcc;
        this.description = description;
        this.ostatok = ostatok;
      //  fillListOfRecords(this);
    }

    //метод заполняет список записей для переданного аккаунта
    private void fillListOfRecords(Account account) {

        DbHelper dbHelper = DbHelper.getDbHerper();
        try {
            records = dbHelper.getRecords(account);
        } catch (SQLException Ignored) {

        }

    }
}
