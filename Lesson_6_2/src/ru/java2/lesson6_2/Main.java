package ru.java2.lesson6_2;


/**
 * Created by Abilis on 10.05.2016.
 *
 * В полях этого класса нужно указать файл, в котором будет проводиться поиск и подстроку, которую будем искать.
 * Файл считывается построчно. Если есть хотя бы одно вхождение, считается что подстрока в строке файла найдена 1 раз
 * При желании можно раскомментировать в классе Consumer вывод на консоль строки, в которой найдена искомая подстрока
 *
 * Кодировка файла должна быть UTF-8
 */
public class Main {

    private static final String FILE_NAME = "D:\\temp\\testfile\\file2.txt";
    private static final String STRING_FOR_SEARCH = "псих";


    public static void main(String[] args) {



        Producer producer = new Producer(FILE_NAME);
        Consumer consumer = new Consumer(STRING_FOR_SEARCH);

        long start = System.currentTimeMillis();

        producer.start();
        consumer.start();

        while (producer.isAlive() || consumer.isAlive()) {

        }


        long finish = System.currentTimeMillis();

        long delta = finish - start;
        System.out.println("Затрачено времени: " + delta + " мс");



    }

}
