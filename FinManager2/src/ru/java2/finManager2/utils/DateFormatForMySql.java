package ru.java2.finManager2.utils;

import java.text.ParseException;
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

    public static Date getDateFormatFromMySql(String str) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = null;

        try {
            result = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            result = new Date();
        }

        return result;
    }

    public static Date getDateFormatFromStringWithParseException(String str) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date result = simpleDateFormat.parse(str);

        return result;
    }

}
