package ru.java2.lesson6_2;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Consumer extends Thread {

    private String stringForSearch;
    private int amountFindString = 0;
    private int amountString = 0;

    public Consumer(String stringForSearch) {
        this.stringForSearch = stringForSearch;
    }


    @Override
    public void run() {

        String line = null;

        try {

            while (!isInterrupted()) {
                line = Buffer.getString();
                if (line == null) {
                    interrupt();
                }
                else {
                    processingString(line);
                }
            }


        } catch (InterruptedException e) {
            System.out.println("Работа потребителя прервана");
        }

        System.out.println("Работа потребителя закончена. Обработано строк: " + amountString + ". Найдено вхождений: " + amountFindString);

    }


    private void processingString(String str) {

        if (str.contains(stringForSearch)) {
            System.out.println("Найдено вхождение подстроки \"" + stringForSearch + "\"!");
//            System.out.println("Найдена строка " + stringForSearch + " в строке:");
            System.out.println(str);
            System.out.println();
            amountFindString++;
        }

        amountString++;
    }

}
