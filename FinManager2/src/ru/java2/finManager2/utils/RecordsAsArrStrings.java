package ru.java2.finManager2.utils;

import ru.java2.finManager2.Record;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Abilis on 21.04.2016.
 */
public class RecordsAsArrStrings {


    //метод получает список транзакций и возвращает массив строк в формате
    //метка, описание, категория, сумма, дата
    public static String[][] getRecordsAsArrOfStrings(ArrayList<Record> records) {

        String[][] result = new String[records.size()][5];

        for (int i = 0; i < result.length; i++) {

            result[i][0] = records.get(i).getLabelAsString();
            result[i][1] = records.get(i).getDescription();
            result[i][2] = records.get(i).getCategoryAsString();
            result[i][3] = records.get(i).getSumAsString();

            //преобразуем дату в формат, удобный для показа в таблице в главном окне приложения
            Date date = records.get(i).getDateOfRecord();
            String dateStr = DateFormatForMySql.getDateFormatForMySql(date);

            result[i][4] = dateStr;
        }

        return result;
    }

}
