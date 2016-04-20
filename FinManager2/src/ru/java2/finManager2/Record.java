package ru.java2.finManager2;

import java.util.Date;

/**
 * Created by Abilis on 19.04.2016.
 */
public class Record {

    private int idRecord;

    private boolean label; //true - пополнение, false - снятие
    private Date dateOfRecord;
    private int sum;
    private  String description;
    private Category category;

    public Record(int idRecord, boolean label, Date dateOfRecord, int sum, String description, Category category) {
        this.idRecord = idRecord;
        this.label = label;
        this.dateOfRecord = dateOfRecord;
        this.sum = sum;
        this.description = description;
        this.category = category;
    }
}
