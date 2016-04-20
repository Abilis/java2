package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
import ru.java2.finManager2.User;
import ru.java2.finManager2.database.DbHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Abilis on 20.04.2016.
 */
public class AddNewAccount {

    private User currentUser;

    //создаем диалоговое окно
    private JDialog addNewAccountFrame = new JDialog();
    private Dimension dimensionAddNewAccountFrame = new Dimension(330, 200);

    //создаем метку-описание того, что здесь происходит
    private JLabel descriptionFrameLabel = new JLabel();

    //создаем метки для обозначения описания аккаунта и остатка на счету
    private JLabel descriptionNewAccLabel = new JLabel("Название нового аккаунта:");
    private JLabel ostatokNewAccLabel = new JLabel("Начальная сумма на счету:");

    //создаем поля ввода для описания аккаунта и остатка на счету
    private JTextField descriptionNewAccTextField = new JTextField(10);
    private JTextField ostatokNewAccTextField = new JTextField(10);

    //создаем пару кнопок: "добавить новый аккаунт" и "закрыть"
    private JButton addNewAccountButton = new JButton("Создать новый аккаунт");
    private JButton closeAddNewAccountFrameButton = new JButton("Закрыть");

    //создаем метку для отображения сообщений об ошибках
    private JLabel messagesLabel = new JLabel();


    public AddNewAccount(User currentUser) {
        this.currentUser = currentUser;
    }

    public void init() {

        //установка настроек формы
        addNewAccountFrame.setSize(dimensionAddNewAccountFrame);
        addNewAccountFrame.setResizable(false);
        addNewAccountFrame.setLocationRelativeTo(null);

        addNewAccountFrame.setLayout(new GridBagLayout());

        //установка настроек метки-описания
        descriptionFrameLabel.setText("Создание нового аккаунта для пользователя " + currentUser.getLogin());

        //установка настроек метки об ошибках
        messagesLabel.setHorizontalAlignment(0);
        messagesLabel.setForeground(Color.RED);

        //расставляем компоненты

        //1 ряд. Метка-описание текущего пользователя
        addNewAccountFrame.add(descriptionFrameLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //2 ряд. Метка и поле ввода описания нового аккаунта
        addNewAccountFrame.add(descriptionNewAccLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        addNewAccountFrame.add(descriptionNewAccTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //3 ряд. Метка и поле ввода остатка на счету
        addNewAccountFrame.add(ostatokNewAccLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        addNewAccountFrame.add(ostatokNewAccTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));

        //4 ряд. Здесь будут отображаться различные сообщения об ошибках
        addNewAccountFrame.add(messagesLabel, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //5 ряд. Две кнопки: "создать новый аккаунт" и "отмена"
        addNewAccountFrame.add(addNewAccountButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));
        addNewAccountFrame.add(closeAddNewAccountFrameButton, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 1));


        //делаем форму видимой
        addNewAccountFrame.setVisible(true);


        //обработчики

        //обработка нажатия на кнопку "отмена"
        closeAddNewAccountFrameButton.addActionListener(new CloseNewAccountFrameActionListener());

        //обработка нажатия на кнопку "создать новый аккаунт"
        addNewAccountButton.addActionListener(new AddNewAccountActionListener());


    }

    class CloseNewAccountFrameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addNewAccountFrame.dispose();
        }
    }

    class AddNewAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String descriptionNewAccount = descriptionNewAccTextField.getText();
            String ostatokNewAccount = ostatokNewAccTextField.getText();



            //Тримим полученные строки
            descriptionNewAccount = descriptionNewAccount.trim();
            ostatokNewAccount = ostatokNewAccount.trim();

            //если хотя бы в одном поле ничего нет - выводим сообщение об ошибке и заканчиваем обработку
            if (descriptionNewAccount.length() == 0 || ostatokNewAccount.length() == 0) {
                messagesLabel.setText("Некорректные данные!");
                return;
            }

            //преобразуем остаток в целое число

            int ostatok = 0;
            try {
                ostatok = Integer.parseInt(ostatokNewAccount);
            } catch (NumberFormatException e2) {
                messagesLabel.setText("Некорректные данные!");
                return;
            }

            //если проверки пройдены - создаем новый аккаунт для текущего пользователя и закрываем это окно

            Account account = new Account(descriptionNewAccount, ostatok);

            DbHelper dbHelper = DbHelper.getDbHerper();
            dbHelper.addAccount(currentUser, account);

            addNewAccountFrame.dispose();
        }
    }

}
