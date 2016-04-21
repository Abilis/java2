package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Category;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 21.04.2016.
 */
public class EditRecord {

    private User currentUser;
    private Account currentAccount;
    private Record currentRecord;

    //создаем форму
    private JFrame editRecordFrame = new JFrame("Финансовый менеджер");
    private Dimension dimensionDeleteRecordFrame = new Dimension(500, 300);

    //создаем метку с описанием того, что здесь можно сделать
    private JLabel commonDescriprionLabel = new JLabel("Отредактировать/удалить транзакцию");

    //создаем метки, описывающие поля транзакции
    private JLabel labelOperationLabel = new JLabel("Метка");
    private JLabel descriptionLabel = new JLabel("Описание");
    private JLabel categoryLabel = new JLabel("Категория");
    private JLabel sumLabel = new JLabel("Сумма");
    private JLabel dateLabel = new JLabel("Дата");

    //Создаем поля ввода, где можно отредактировать транзакцию
    private JComboBox labelComboBox = new JComboBox();
    private JTextField descriptionTextField = new JTextField();
    private JComboBox categoryComboBox = new JComboBox(Category.values());
    private JTextField sumTextField = new JTextField();
    private JTextField dataTextField = new JTextField();

    //создаем кнопку "отредактировать"
    private JButton editButton = new JButton("Отредактировать");

    //создаем кнопку "удалить"
    private JButton deleteButton = new JButton("Удалить");

    //создаем кнопку "отмена"
    private JButton cancelButton = new JButton("Отмена");




    public EditRecord() {}

    public void init() {

        //устанавливаем настроки формы
        editRecordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editRecordFrame.setSize(dimensionDeleteRecordFrame);
        editRecordFrame.setResizable(false);
        editRecordFrame.setLocationRelativeTo(null);

        editRecordFrame.setLayout(new GridBagLayout());

        //устанавливаем настройки метки транзакции


        //расставляем компоненты

        //1 ряд. Метка с общий сообщением
        editRecordFrame.add(commonDescriprionLabel, new GridBagConstraints(0, 0, 5, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //2 ряд. Метки с описанием полей транзакции
        editRecordFrame.add(labelOperationLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        editRecordFrame.add(descriptionLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        editRecordFrame.add(categoryLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        editRecordFrame.add(sumLabel, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        editRecordFrame.add(dateLabel, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //3 ряд. Поля транзакции

        //метка снятие-пополение
        editRecordFrame.add(labelComboBox, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //описание
        editRecordFrame.add(descriptionTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //категория
        editRecordFrame.add(categoryComboBox, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //сумма
        editRecordFrame.add(sumTextField, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //дата
        editRecordFrame.add(dataTextField, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //4 ряд. Кнопки
        editRecordFrame.add(editButton, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        editRecordFrame.add(deleteButton, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        editRecordFrame.add(cancelButton, new GridBagConstraints(2, 3, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //делаем форму видимой
        editRecordFrame.setVisible(true);


    }

}
