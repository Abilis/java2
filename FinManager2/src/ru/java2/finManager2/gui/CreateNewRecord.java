package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.Category;
import ru.java2.finManager2.Record;
import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;
import ru.java2.finManager2.exceptions.DontAddRecordException;
import ru.java2.finManager2.exceptions.NoEnoughtMoneyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Abilis on 20.04.2016.
 */
public class CreateNewRecord {

    private User currentUser;
    private Account currentAccount;

    //создаем форму
    private JFrame createNewRecordFrame = new JFrame("Финансовый менеджер");
    private Dimension dimensionCreateNewRecordFrame = new Dimension(300, 270);

    //cоздаем пару радиокнопок (пополнение/снятие)
    private JRadioButton addingRadioButton = new JRadioButton("Пополнение");
    private JRadioButton withdrawRadioButton = new JRadioButton("Снятие");

    //и группу для них
    private ButtonGroup radiobuttonsGroupButton = new ButtonGroup();

    //создаем метку суммы
    private JLabel sumLabel = new JLabel("Сумма:");

    //создаем поле ввода суммы
    private JTextField sumTextField = new JTextField(10);

    //создаем метку описания
    private JLabel descriptionLabel = new JLabel("Описание: ");

    //создаем поле ввода описания
    private JTextField descriptionTextField = new JTextField(10);

    //создаем выпадающий список категорий
    private JComboBox categories = new JComboBox(Category.values());

    //создаем скрытую метку, куда будут выводить сообщения об ошибках
    private JLabel messagesLabel = new JLabel();

    //создаем пару кнопок "Добавить" и "Отмена"
    private JButton addButton = new JButton("Добавить");
    private JButton cancelButton = new JButton("Отмена");


    public CreateNewRecord(User currentUser, Account account) {
        this.currentUser = currentUser;
        this.currentAccount = account;
    }

    public void init() {

        //установка настроек формы
        createNewRecordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createNewRecordFrame.setSize(dimensionCreateNewRecordFrame);
        createNewRecordFrame.setResizable(false);
        createNewRecordFrame.setLocationRelativeTo(null);

        createNewRecordFrame.setLayout(new GridBagLayout());

        //установка выбранной радиокнопки по умолчанию
        withdrawRadioButton.setSelected(true);

        //установка параметров скрытой метки
        messagesLabel.setHorizontalAlignment(0);
        messagesLabel.setForeground(Color.RED);

        //расстановка компонентов

        //1 и 2 ряд - группируем радиокнопки
        radiobuttonsGroupButton.add(addingRadioButton);
        radiobuttonsGroupButton.add(withdrawRadioButton);
        createNewRecordFrame.add(addingRadioButton, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        createNewRecordFrame.add(withdrawRadioButton, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //3 ряд. Ставим метку суммы и поле ввода суммы
        createNewRecordFrame.add(sumLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        createNewRecordFrame.add(sumTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //4 ряд. Ставим метку описания и поле ввода описания
        createNewRecordFrame.add(descriptionLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        createNewRecordFrame.add(descriptionTextField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //5 ряд. Ставим выпадающий список доступных категорий
        createNewRecordFrame.add(categories, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //6 ряд. Ставим скрытую метку для сообщений об ощибках
        createNewRecordFrame.add(messagesLabel, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //7 ряд. Ставим кнопки "добавить" и "отмена
        createNewRecordFrame.add(addButton, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        createNewRecordFrame.add(cancelButton, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //делаем форму видимой
        createNewRecordFrame.setVisible(true);


        //обработчики

        //обработка нажатия на кнопку "добавить"
        addButton.addActionListener(new AddNewRecordButtonActionListener());

        //обработка нажатия на кнопку "отмена"
        cancelButton.addActionListener(new CancelButtonActionListener());

        //обработка нажатия клавиши "Энтер" на кнопке "добавить"
        addButton.addKeyListener(new AddNewRecordByPressEnterOnButtonAddNewRecord());

        //обработка нажатия клавиши "Энтер" на кнопке "отмена"
        cancelButton.addKeyListener(new CancelByPressEnterOnButtonCancel());

        //обработка нажатия клавиши "Энтер" в поле ввода суммы
        sumTextField.addKeyListener(new AddNewRecordByPressEnterInDescriptionTextField());

        //обработка нажатия клавиши "Энтер" в поле ввода описания
        descriptionTextField.addKeyListener(new AddNewRecordByPressEnterInDescriptionTextField());

        


    }

    class AddNewRecordButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //разбираем то, что введено в форме

            boolean label = false;
            String sumStr = sumTextField.getText();
            int sum = 0;
            String description = descriptionTextField.getText();
            Category category = (Category) categories.getSelectedItem();

            //тримим сумму и описание
            sumStr = sumStr.trim();
            description = description.trim();

            //проверка на валидность введенных данных
            if (sumStr.isEmpty() || description.isEmpty()) {
                messagesLabel.setText("Некорректные данные");
                return;
            }

            //установка метки
            if (addingRadioButton.isSelected()) {
                label = true;
            }
            else {
                label = false;
            }

            //парсим сумму в число. Допускаются только положительные числа
            try {
                sum = Integer.parseInt(sumStr);

                if (sum <= 0) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException Ignored) {
                messagesLabel.setText("Некорректные данные");
                return;
            }

            //создаем новую запись
            Record record = new Record(label, new Date(), sum, description, category);

            //Передаем управление DbHelper
            DbHelper dbHelper = DbHelper.getDbHerper();

            try {
                dbHelper.addRecord(currentAccount, record);
                //если не произошло ничего страшного - закрываем эту форму и открываем главное окно приложения

                createNewRecordFrame.dispose();

                MainWindow mainWindow = new MainWindow(currentUser);
                mainWindow.init();

            } catch (SQLException e1) {
                messagesLabel.setText("Ошибка при записи в БД. Попробуйте еще раз");
                e1.printStackTrace();
            } catch (DontAddRecordException | NoEnoughtMoneyException e1) {
                messagesLabel.setText(e1.getMessage());

            }
        }
    }

    class CancelButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            createNewRecordFrame.dispose();
            MainWindow mainWindow = new MainWindow(currentUser);
            mainWindow.init();

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

    class CancelByPressEnterOnButtonCancel extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new CancelButtonActionListener ().actionPerformed(ae);
            }
        }
    }

    class AddNewRecordByPressEnterInSumTextField extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewRecordButtonActionListener ().actionPerformed(ae);
            }
        }
    }

    class AddNewRecordByPressEnterInDescriptionTextField extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ActionEvent ae = new ActionEvent(e, 0, "test");
                new AddNewRecordButtonActionListener ().actionPerformed(ae);
            }
        }
    }

}
