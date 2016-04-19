package ru.java2.lesson_3_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Abilis on 19.04.2016.
 */
public class MainWindowFrame {

    private User currentUser;

    //создаем форму
    private JFrame mainWindowFrame = new JFrame("Финансовый менеджер");
    private Dimension dimension = new Dimension(600, 400);

    //создаем группу радиокнопок
    private ButtonGroup buttonGroupForRadioButton = new ButtonGroup();

    //создаем тройку радиобаттонов
    private JRadioButton radioButton1 = new JRadioButton("Первая радиокнопка", true);
    private JRadioButton radioButton2 = new JRadioButton("Вторая радиокнопка");
    private JRadioButton radioButton3 = new JRadioButton("Третья радиокнопка");

    //создаем кнопку
    private JButton buttonForThreeRadioButtons = new JButton("Вывести активное");


    public void initMainWindowFrame(User user) {

        //установка текущего пользователя
        currentUser = user;

        //устанавливаем параметры фрейма
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setSize(dimension);
        mainWindowFrame.setResizable(false);
        mainWindowFrame.setLocationRelativeTo(null);

        mainWindowFrame.setLayout(new GridBagLayout());

        //добавляем радиокнопки в группу
        buttonGroupForRadioButton.add(radioButton1);
        buttonGroupForRadioButton.add(radioButton2);
        buttonGroupForRadioButton.add(radioButton3);



        //добавляем на форму радиокнопки
        mainWindowFrame.add(radioButton1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        mainWindowFrame.add(radioButton2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        mainWindowFrame.add(radioButton3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        //добавляем на форму кнопку-обработчик радиокнопок
        mainWindowFrame.add(buttonForThreeRadioButtons, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));


        //делаем окно видимым
        mainWindowFrame.setVisible(true);


        //обработчики
        //Обработка группы радиокнопок
        buttonForThreeRadioButtons.addActionListener(new ButtonForThreeRadioButtonsActionListener());

    }

    class ButtonForThreeRadioButtonsActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (radioButton1.isSelected()) {
                System.out.println(radioButton1.getText());
            }
            else if (radioButton2.isSelected()) {
                System.out.println(radioButton2.getText());
            }
            else if (radioButton3.isSelected()) {
                System.out.println(radioButton3.getText());
            }

        }
    }
}
