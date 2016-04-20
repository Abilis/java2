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
    private ArrayList<Record> records = new ArrayList<Record>();

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
    }


    //метод заполняет аккаунт записями из БД
    public void fillAccountRecords() throws SQLException {
        DbHelper dbHelper = DbHelper.getDbHerper();

        records = dbHelper.getRecords(this);
    }


    @Override
    public String toString() {
        return description + ", остаток на счете: " + ostatok;
    }

}
