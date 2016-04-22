package ru.java2.finManager2;

import java.text.SimpleDateFormat;
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

    public int getIdRecord() {
        return idRecord;
    }

    public boolean isLabel() {
        return label;
    }

    public Date getDateOfRecord() {
        return dateOfRecord;
    }

    public int getSum() {
        return sum;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Record(int idRecord, boolean label, Date dateOfRecord, int sum, String description, Category category) {
        this.idRecord = idRecord;
        this.label = label;
        this.dateOfRecord = dateOfRecord;
        this.sum = sum;
        this.description = description;
        this.category = category;
    }

    public Record(boolean label, Date dateOfRecord, int sum, String description, Category category) {
        this.label = label;
        this.dateOfRecord = dateOfRecord;
        this.sum = sum;
        this.description = description;
        this.category = category;
    }


    //тестовый туСтринг
    @Override
    public String toString() {
        return "Record{" +
                "idRecord=" + idRecord +
                ", label=" + label +
                ", dateOfRecord=" + dateOfRecord +
                ", sum=" + sum +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

    public String getLabelAsString() {
        if (label) {
            return "Пополнение";
        }
        else {
            return "Снятие";
        }
    }

    public String getCategoryAsString() {
        return category.toString();
    }

    public String getSumAsString() {
        return String.valueOf(sum);
    }


    public static String[] getArrLabelLalues() {
        String[] result = new String[2];
        result[0] = "Пополнение";
        result[1] = "Снятие";

        return result;
    }

}
