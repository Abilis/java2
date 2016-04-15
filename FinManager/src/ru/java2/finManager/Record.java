package ru.java2.finManager;

import java.util.Date;

/**
 * Created by Abilis on 04.04.2016.
 */
public class Record {

    private int label; //1 - пополнение, 0 - снятие
    private Date dateOfTransaction;
    private int sum;
    private String description;
    private String category;

    public Record(int label, Date dateOfTransaction, int sum, String description, String category) {
        this.label = label;
        this.dateOfTransaction = dateOfTransaction;
        this.sum = sum;
        this.description = description;
        this.category = category;
    }

    @Override
    public String toString() {
        return  dateOfTransaction + ": " + getFormatLabel(label) + ", сумма: " + sum + ", описание: " + description +
                ", категория: " + category + "\n";
    }

    private String getFormatLabel(int label) {
        String formatlabel = "Неизвестная операция";
        if (label == 0) {
            formatlabel = "снятие";
        }
        else if (label == 1) {
            formatlabel = "пополнение";
        }

        return formatlabel;
    }
}
