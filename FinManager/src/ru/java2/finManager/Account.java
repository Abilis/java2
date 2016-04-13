package ru.java2.finManager;


import java.util.ArrayList;


/**
 * Created by Abilis on 04.04.2016.
 */
public class Account {

    private int idAccount;
    private String description;
    private int ostatok;
    private ArrayList<Record> listOfRecords;
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
