package ru.java2.finManager2.tableModels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by Abilis on 21.04.2016.
 */
public class RecordsTableModel extends AbstractTableModel {

    private int columnCount = 5;
    private ArrayList<String[]> dataArrayList = new ArrayList<String[]>();

    private String[] getStringArrInDataArrayList(int index) {
        return dataArrayList.get(index);
    }

    public RecordsTableModel() {
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dataArrayList.get(rowIndex)[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        String value = "";

        if (aValue instanceof String) {
            value = (String) aValue;
        }

        //массив строк, соответствующий строке в таблице
        String[] arr = getStringArrInDataArrayList(rowIndex);

        //заменяем элемент массива переданной строкой
        arr[columnIndex] = value;

    }

    //метод добавляет 1 строку в список данных таблицы
    private void addData(String[] row) {
        dataArrayList.add(row);
    }

    //метод добавляет все переданные строки в список данных таблицы
    public void addDataAll(String[][] rows) {
        for (int i = 0; i < rows.length; i++) {
            addData(rows[i]);
        }
    }

    //метод очищает список с транзакциями
    public void clearDataAll() {
        dataArrayList.clear();
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {

            case 0:
                return "Метка";
            case 1:
                return "Описание";
            case 2:
                return "Категория";
            case 3:
                return "Сумма";
            case 4:
                return "Дата";
        }

        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
