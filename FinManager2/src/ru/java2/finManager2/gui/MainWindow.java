package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Category;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;
import ru.java2.finManager2.tableModels.RecordsTableModel;
import ru.java2.finManager2.utils.RecordsAsArrStrings;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Abilis on 20.04.2016.
 */
public class MainWindow {

    private User currentUser; //текущий пользователь
    private ArrayList<Account> accounts = new ArrayList<Account>(); //список аккаунтов текущего пользователя
    private Account currentAccount; //текущий аккаунт пользователя

    private JFrame mainWindowFrame = new JFrame("Финансовый менеджер");
    private Dimension dimensionMainWindowFrame = new Dimension(800, 700);

    //создаем метку, где находится имя пользователя
    private JLabel usernameLabel = new JLabel();

    //создаем метку, где отображается остаток на счету
    private JLabel ostatokLabel = new JLabel();

    //создаем метку, где будут отображаться сообщения об ошибках
    private JLabel mesLabel = new JLabel();

    //создаем метку, описывающую выпадающий список
    private JLabel listOfAccountLabel = new JLabel("Текущий аккаунт:");

    //создаем выпадающий список
    private JComboBox listOfAccounts;


    //создаем модель таблицы, где будут отображаться транзакции
    private RecordsTableModel recordsTableModel = new RecordsTableModel();

    //создаем таблицу, где будут отображаться транзакции
    private JTable recordsTable = new JTable(recordsTableModel);

    //создаем панель прокрутки, куда поместим таблицу с транзакциями
    private JScrollPane scrollPaneForRecordsTable = new JScrollPane(recordsTable);

    //задаем размеры панели прокрутки
    private Dimension dimensionOfScroolPane = new Dimension(100, 100);


    //создаем кнопку "создать новый аккаунт"
    private JButton addNewAccountButton = new JButton("Создать новый аккаунт");

    //создаем кнопку "сменить пользователя"
    private JButton changeUserButton = new JButton("Сменить пользователя");

    //создаем кнопку "добавить транзакцию"
    private JButton addRecordButton = new JButton("Добавить транзакцию");

    //создаем кнопку "закрыть"
    private JButton closeAppButton = new JButton("Закрыть");



    public MainWindow(User currentUser) {
        this.currentUser = currentUser;
        usernameLabel.setText(currentUser.getLogin());
        DbHelper dbHelper = DbHelper.getDbHerper();

        try {
            accounts = dbHelper.getAccounts(currentUser); //инициализация аккаунтов текущего пользователя

            currentUser.fillAccountsUser(accounts); //заполняем записями все аккаунты текущего пользователя

            currentUser.setAccounts(accounts); //записываем в поле текущего пользователя полученный список аккаунтов

            if (accounts.size() != 0) {
                currentAccount = accounts.get(0); //установка аккаунта по умолчанию
                ostatokLabel.setText("Баланс: " + String.valueOf(currentAccount.getOstatok()));
            }



        } catch (SQLException e) {
            e.printStackTrace();
            mesLabel.setText("Не удалось получить аккаунты для " + currentUser.getLogin());
        }


    }

    //инициализация формы
    public void init() {

        //установка настроек формы
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setResizable(false);
        mainWindowFrame.setSize(dimensionMainWindowFrame);
        mainWindowFrame.setLocationRelativeTo(null);

        mainWindowFrame.setLayout(new GridBagLayout());

        //установка настроек метки с сообщениями об ошибках
        mesLabel.setHorizontalAlignment(0);
        mesLabel.setForeground(Color.RED);

        //установка настроек выпадающего списка аккаунтов
        //инициализируем listOfAccounts массивом строк, описывающих аккаунт
        listOfAccounts = new JComboBox(currentUser.getArrOfAccounts());

        //установка настроек таблицы транзакций
        //установка размера панели прокрутки
        scrollPaneForRecordsTable.setSize(dimensionOfScroolPane);

        //Преобразуем список транзакций текущего аккаунта в список массива строк

        if (currentAccount != null) {
            String[][] recordsAsStrArr = RecordsAsArrStrings.getRecordsAsArrOfStrings(currentAccount.getRecords());

            //заполняем таблицу
            recordsTableModel.addDataAll(recordsAsStrArr);
        }


        //раскрашиваем строки таблицы с транзакциями в зависимости от категории
        for (int i = 0; i < recordsTableModel.getColumnCount(); i++) {
            recordsTable.getColumnModel().getColumn(i).setCellRenderer(new TableRendererByCategories());
        }


        //расставляем компоненты

        //1 ряд, самый верх. Метки с именем текущего пользователя и остатком на выбранном аккаунте
        mainWindowFrame.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        mainWindowFrame.add(ostatokLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //2 ряд. Метка с сообщениями об ошибках
        mainWindowFrame.add(mesLabel, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //3 ряд. Метка, описывающая список аккаунтов
        mainWindowFrame.add(listOfAccountLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //4 ряд. Выпадающий список аккаунтов
        mainWindowFrame.add(listOfAccounts, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //5 ряд. Пролистываемый список записей в виде таблицы
        mainWindowFrame.add(scrollPaneForRecordsTable, new GridBagConstraints(0, 4, 2, 2, 1.0, 1.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //6 ряд. Кнопки "создать новый аккаунт" и "сменить пользователя"
        mainWindowFrame.add(addNewAccountButton, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        mainWindowFrame.add(changeUserButton, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //7 ряд. Кнопки "добавить транзакцию" и "закрыть"
        mainWindowFrame.add(addRecordButton, new GridBagConstraints(0, 7, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        mainWindowFrame.add(closeAppButton, new GridBagConstraints(1, 7, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //делаем форму видимой
        mainWindowFrame.setVisible(true);




        //обработчики событий

        //обработка нажатия кнопки "закрыть"
        closeAppButton.addActionListener(new CloseButtonActionListener());

        //обработка нажатия кнопки "создать новый аккаунт"
        addNewAccountButton.addActionListener(new AddNewAccountActionListener());

        //обработка нажатия кнопки "Добавить транзакцию"
        addRecordButton.addActionListener(new AddNewRecordButtonActionListener());

        //обработка нажатия кнопки "Сменить пользователя"
        changeUserButton.addActionListener(new ChangeUserButtonActionListener());

        //обработка нажатия клавиши "Энтер" на кнопке "Создать новый аккаунт"
        addNewAccountButton.addKeyListener(new AddNewAccountByPressEnterOnButonAddNewAccount());

        //обработка нажатия клавиши "Энтер" на кнопке "Добавить транзакцию"
        addRecordButton.addKeyListener(new AddNewRecordByPressEnterOnButtonAddNewRecord());

        //обработка нажатия клавиши "Энтер" на кнопке "Закрыть
        closeAppButton.addKeyListener(new CloseMainFrameByPressEnterOnButtonClose());

        //обработка нажатия клавиши "Энтер" на кнопке "Сменить пользователя"
        changeUserButton.addKeyListener(new ChangeUserByPressEnterOnButtonChangeUser());

        //обработка выбора текущего аккаунта
        listOfAccounts.addActionListener(new SelectedAccountActionListener());

        //обработка клика на строку транзакции
        ListSelectionModel listSelectionModel = recordsTable.getSelectionModel();

        listSelectionModel.addListSelectionListener(new EditRecordSelectionListener());




    }

    class CloseButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainWindowFrame.dispose();
        }
    }

    class AddNewAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //нужно создать новое окно, где пользователь может ввести/выбрать данные нового аккаунта
            AddNewAccount addNewAccount = new AddNewAccount(currentUser);
            mainWindowFrame.dispose();
            addNewAccount.init();

        }
    }

    class CloseMainFrameByPressEnterOnButtonClose extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new CloseButtonActionListener().actionPerformed(ae);
            }
        }
    }

    class AddNewAccountByPressEnterOnButonAddNewAccount extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewAccountActionListener ().actionPerformed(ae);
            }
        }
    }

    class AddNewRecordButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            mainWindowFrame.dispose();
            CreateNewRecord createNewRecord = new CreateNewRecord(currentUser, currentAccount);
            createNewRecord.init();

        }
    }

    class AddNewRecordByPressEnterOnButtonAddNewRecord extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewRecordButtonActionListener ().actionPerformed(ae);
            }
        }
    }

    class ChangeUserButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //закрываем это окно и открываем окно логина и создания нового пользователя
            mainWindowFrame.dispose();
            LoginAndRegistration loginAndRegistration = new LoginAndRegistration();
            loginAndRegistration.init();
        }
    }

    class ChangeUserByPressEnterOnButtonChangeUser extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new ChangeUserButtonActionListener ().actionPerformed(ae);
            }
        }
    }

    class SelectedAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //определяем индекс выбранного аккаунта
            int currentAccIndex = listOfAccounts.getSelectedIndex();

            //делаем активным его
            currentAccount = accounts.get(currentAccIndex);

            //меняем метку остатка средств на аккаунте
            ostatokLabel.setText("Баланс: " + String.valueOf(currentAccount.getOstatok()));

            //Преобразуем список транзакций текущего аккаунта в список массива строк
            String[][] recordsAsStrArr = RecordsAsArrStrings.getRecordsAsArrOfStrings(currentAccount.getRecords());

            //очищаем таблицу, обновляем форму и заполняем транзакциями заново
            recordsTableModel.clearDataAll();
            recordsTableModel.fireTableDataChanged();
            recordsTableModel.addDataAll(recordsAsStrArr);

        }
    }

    class EditRecordSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

            if (!e.getValueIsAdjusting()) {
                int[] rec = recordsTable.getSelectedRows();

                //индекс в rec[0] есть индекс транзакции в списке транзакций текущего аккаунта

                Record selectedRecord = currentAccount.getRecords().get(rec[0]);

                mainWindowFrame.dispose();

                EditRecord editRecord = new EditRecord(selectedRecord, currentAccount, currentUser);
                editRecord.init();
            }


        }
    }

    class TableRendererByCategories extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {


            Component cell =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value.equals(Category.CLOTHES.toString())) {
                cell.setBackground(Color.GRAY);
            }
            else if (value.equals(Category.FOOD.toString())) {
                cell.setBackground(Color.GREEN);
            }
            else if (value.equals(Category.HEALTH.toString())) {
                cell.setBackground(Color.RED);
            }
            else if (value.equals(Category.TRAVELLING.toString())) {
                cell.setBackground(Color.ORANGE);
            }
            else if (value.equals(Category.OTHER.toString())) {
                cell.setBackground(Color.CYAN);
            }


            return cell;

        }


    }


}
