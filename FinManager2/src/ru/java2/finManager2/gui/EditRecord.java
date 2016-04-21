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
    private Dimension dimensionDeleteRecordFrame = new Dimension(700, 300);

    //создаем метку с описанием того, что здесь можно сделать
    private JLabel commonDescriprionLabel = new JLabel("Отредактировать/удалить транзакцию");

    //создаем метки, описывающие поля транзакции
    private JLabel labelOperationLabel = new JLabel("Метка");
    private JLabel descriptionLabel = new JLabel("Описание");
    private JLabel categoryLabel = new JLabel("Категория");
    private JLabel sumLabel = new JLabel("Сумма");
    private JLabel dateLabel = new JLabel("Дата");

    //Создаем поля ввода, где можно отредактировать транзакцию
    private JComboBox labelComboBox = new JComboBox(Record.getArrLabelLalues());
    private JTextField descriptionTextField = new JTextField();
    private JComboBox categoryComboBox = new JComboBox(Category.values());
    private JTextField sumTextField = new JTextField();
    private JTextField dataTextField = new JTextField();

    //создаем метку, где будут выводиться сообщения об ошибках
    private JLabel messagesLabel = new JLabel();

    //создаем панель, где будут располагаться кнопки
    private JPanel buttonsPanel = new JPanel();

    //создаем кнопку "отредактировать"
    private JButton editButton = new JButton("Отредактировать");

    //создаем кнопку "удалить"
    private JButton deleteButton = new JButton("Удалить");

    //создаем кнопку "отмена"
    private JButton cancelButton = new JButton("Отмена");


    public EditRecord(Record selectedRecord, Account currentAccount, User currentUser) {
        currentRecord = selectedRecord;
        this.currentAccount = currentAccount;
        this.currentUser = currentUser;

        //инициализация полей транзакции

        //инициализация метки
        labelComboBox.setSelectedItem(currentRecord.getLabelAsString());

        //инициализация описания
        descriptionTextField.setText(currentRecord.getDescription());

        //инициализация категории
        categoryComboBox.setSelectedIndex(Category.getIndex(currentRecord.getCategory()));

        //инициализация суммы
        sumTextField.setText(String.valueOf(currentRecord.getSum()));

        //инициализация даты
        dataTextField.setText(currentRecord.getDateOfRecordAsString());
    }

    public void init() {

        //устанавливаем настроки формы
        editRecordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editRecordFrame.setSize(dimensionDeleteRecordFrame);
        editRecordFrame.setResizable(false);
        editRecordFrame.setLocationRelativeTo(null);

        editRecordFrame.setLayout(new GridBagLayout());

        //устанавливаем настройки метки с описанием того, что тут можно сделать
        commonDescriprionLabel.setHorizontalAlignment(0);
        commonDescriprionLabel.setFont(new Font("Arial", Font.PLAIN , 20));

        //устанавливаем настройки панели для кнопок
        buttonsPanel.setLayout(new GridBagLayout());


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

        //4 ряд. Метка с сообщениями об ошибках
        editRecordFrame.add(messagesLabel, new GridBagConstraints(0, 3, 5, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //5 ряд. Кнопки на панели
        buttonsPanel.add(editButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        buttonsPanel.add(deleteButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        buttonsPanel.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //Теперь ставим саму папель
        editRecordFrame.add(buttonsPanel, new GridBagConstraints(0, 4, 5, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));




        //делаем форму видимой
        editRecordFrame.setVisible(true);


    }

}
