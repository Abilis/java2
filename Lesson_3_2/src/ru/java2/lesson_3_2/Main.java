package ru.java2.lesson_3_2;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Abilis on 18.04.2016.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        JFrame mainWindow = new JFrame("Финансовый менеджер");

        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(600, 400);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(new GridBagLayout());

        JLabel labelLoading = new JLabel("Идет загрузка...");
        JProgressBar progressBar = new JProgressBar();

        mainWindow.add(labelLoading);
        mainWindow.add(progressBar);

        progressBar.setMaximum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);

        mainWindow.setVisible(true);

        while (progressBar.getValue() < progressBar.getMaximum()) {

            int currentPercent = progressBar.getValue();

            if (progressBar.getValue() < 50) {
                currentPercent++;
            }
            else if (progressBar.getValue() < 85) {
                currentPercent += 3;
            }
            else {
                currentPercent++;
                Thread.sleep(15);
            }
            progressBar.setValue(currentPercent);
            Thread.sleep(10);
        }


        labelLoading.setText("Загружено!");


    }


}
