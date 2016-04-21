package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Category;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

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


        //обработчики

        //обработка нажатия на кнопку "отредактировать"
        editButton.addActionListener(new EditButtonActionListener());

        //обработка нажатия клавиши "Энтер" на кнопку "отредактировать"
        editButton.addKeyListener(new EditByPressEnterOnButtonEdit());

        //обработка нажатия на кнопку "удалить"
        deleteButton.addActionListener(new DeleteButtonActionListener());

        //обработка нажатия клавиши "Энтер" на кнопку "удалить"
        deleteButton.addKeyListener(new DeleteByPressEnterOnButtonDelete());

        //обработка нажатия на кнопку "отмена"
        cancelButton.addActionListener(new CancelButtonActionListener());

        //обработка нажатия клавиши "Энтер" на кнопку "отмена"
        cancelButton.addKeyListener(new CancelByPressEnterOnButtonCancel());

    }

    class CancelButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editRecordFrame.dispose();
            MainWindow mainWindow = new MainWindow(currentUser);
            mainWindow.init();
        }
    }

    class CancelByPressEnterOnButtonCancel extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new CancelButtonActionListener ().actionPerformed(ae);
            }
        }
    }

    class EditButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Редактирование транзакции");
        }
    }

    class EditByPressEnterOnButtonEdit extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new EditButtonActionListener().actionPerformed(ae);
            }
        }
    }

    class DeleteButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DbHelper dbHelper = DbHelper.getDbHerper();
            try {
                dbHelper.removeRecord(currentAccount, currentRecord);

                //если все нормально - закрываем это окно и отрываем главное
                editRecordFrame.dispose();
                MainWindow mainWindow = new MainWindow(currentUser);
                mainWindow.init();

            } catch (SQLException e1) {
                messagesLabel.setText(e1.getMessage());
            }



        }
    }

    class DeleteByPressEnterOnButtonDelete extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new DeleteButtonActionListener().actionPerformed(ae);
            }
        }
    }
}
