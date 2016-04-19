package ru.java2.finManager2.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 20.04.2016.
 */
public class MainWindow {

    private JFrame mainWindowFrame = new JFrame("Финансовый менеджер");
    private Dimension dimensionMainWindowFrame = new Dimension(600, 400);

    //инициализация формы
    public void init() {

        //установка настроек формы
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setLocationRelativeTo(null);
        mainWindowFrame.setResizable(false);
        mainWindowFrame.setSize(dimensionMainWindowFrame);

        mainWindowFrame.setLayout(new GridBagLayout());



        //делаем форму видимой
        mainWindowFrame.setVisible(true);

    }
}
