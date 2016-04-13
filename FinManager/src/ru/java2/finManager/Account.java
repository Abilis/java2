package ru.java2.finManager;


import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Abilis on 04.04.2016.
 */
public class Account {

    private int idAccount;
    private String description;
    private int ostatok;
    private ArrayList<Record> listOfRecords;

    //если поле listOfRecords не инициализировано, то вытаскиваются данные из БД
    public ArrayList<Record> getListOfRecords() {
        if (listOfRecords == null) {
            DbHelper dbHelper = DbHelper.getDbHelper();
            try {
                listOfRecords = dbHelper.getRecords(this.idAccount);
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Не удалось получить список записей из БД для аккаунта с id " + idAccount);
            }
        }
        return listOfRecords;
    }

    private int idUser;

    public Account(int idAccount, String description, int ostatok, int idUser) {
        this.idAccount = idAccount;
        this.description = description;
        this.ostatok = ostatok;
        this.idUser = idUser;
    }


    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", description='" + description + '\'' +
                ", ostatok=" + ostatok +
                ", listOfRecords=" + listOfRecords +
                ", idUser=" + idUser +
                '}';
    }
}
