package ru.java2.lesson6_2;

/**
 * Created by Abilis on 10.05.2016.
 */
public class Consumer extends Thread {

    private static String STRING_FOR_SEARCH;
    private static int count = 0;

    public Consumer(String stringForSearch) {
        STRING_FOR_SEARCH = stringForSearch;
    }


    @Override
    public void run() {

        while (!isInterrupted()) {
            String str = null;

            try {
                str = Buffer.getString();
                processingString(str);
            } catch (InterruptedException e) {
                System.out.println("Потребитель закончил работу из-за пустой очереди");
            }
        }

        System.out.println("Разбор файла потребителем завершен! Найдено подстрок \"" + STRING_FOR_SEARCH + "\": " + count);

    }

    private void processingString(String str) {

        if (str == null) {
            interrupt();
            return;
        }

        if (str.contains(STRING_FOR_SEARCH)) {
            count++;
            System.out.println("*****************");
            System.out.println("Найдена подстрока " + STRING_FOR_SEARCH + "! Всего найдено подстрок в файле: " + count);
            System.out.println("*****************");
        }

    }

}
