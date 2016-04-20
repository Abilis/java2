package ru.java2.finManager2.gui;

import ru.java2.finManager2.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Abilis on 20.04.2016.
 */
public class CreateNewRecord {

    private User currentUser;

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

    //создаем скрытую метку, куда будут выводить сообщения об ошибках
    private JLabel messagesLabel = new JLabel();

    //создаем пару кнопок "Добавить" и "Отмена"
    private JButton addButton = new JButton("Добавить");
    private JButton cancelButton = new JButton("Отмена");


    public CreateNewRecord(User currentUser) {
        this.currentUser = currentUser;
    }

    public void init() {

        //установка настроек формы
        createNewRecordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createNewRecordFrame.setSize(dimensionCreateNewRecordFrame);
        createNewRecordFrame.setResizable(false);
        createNewRecordFrame.setLocationRelativeTo(null);

        createNewRecordFrame.setLayout(new GridBagLayout());

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
    }

    class AddNewRecordButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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

}
