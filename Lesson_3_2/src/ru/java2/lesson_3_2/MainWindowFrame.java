package ru.java2.lesson_3_2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 19.04.2016.
 */
public class MainWindowFrame {

    private User currentUser;

    private JFrame mainWindowFrame = new JFrame("Финансовый менеджер");
    private Dimension dimension = new Dimension(600, 400);



    public void initMainWindowFrame(User user) {

        //установка текущего пользователя
        currentUser = user;

        //устанавливаем параметры фрейма
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setSize(dimension);
        mainWindowFrame.setResizable(false);
        mainWindowFrame.setLocationRelativeTo(null);

        mainWindowFrame.setLayout(new GridBagLayout());


        //делаем окно видимым
        mainWindowFrame.setVisible(true);

    }

}
