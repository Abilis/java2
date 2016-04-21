package ru.java2.finManager2.gui;

import ru.java2.finManager2.Account;
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
    private JFrame deleteRecordFrame = new JFrame("Финансовый менеджер");
    private Dimension dimensionDeleteRecordFrame = new Dimension(500, 300);




    public void init() {

        //устанавливаем настроки формы
        deleteRecordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteRecordFrame.setSize(dimensionDeleteRecordFrame);
        deleteRecordFrame.setResizable(false);
        deleteRecordFrame.setLocationRelativeTo(null);

        deleteRecordFrame.setLayout(new GridBagLayout());


        //делаем форму видимой
        deleteRecordFrame.setVisible(true);


    }

}
