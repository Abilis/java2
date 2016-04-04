package ru.java2.finManager;

import java.util.Date;

/**
 * Created by Abilis on 04.04.2016.
 */
public class Record {

    private boolean label; //true - пополнение, false - снятие
    private Date dateOfTransaction;
    private int sum;
    private String description;

}
