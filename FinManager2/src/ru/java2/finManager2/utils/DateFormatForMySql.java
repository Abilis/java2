package ru.java2.finManager2.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Abilis on 21.04.2016.
 */
public class DateFormatForMySql {

    public static String getDateFormatForMySql(Date date) {

        //2016-04-20 06:19:26
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = simpleDateFormat.format(date);

        return result;
    }

}
