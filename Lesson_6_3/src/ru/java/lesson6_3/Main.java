package ru.java.lesson6_3;



import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Abilis on 11.05.2016.
 *
 * Производится поиск по всем файлам в указанной директории. Нужно задать строку для поиска и общее количество потоков.
 * Число потребителей по умолчанию равно четверти от количества всех потоков.
 *
 * При начале обработки очередного файла об этом выводится сообщение в консоль.
 * Также при смерти очередного потока об этом сообщается.
 *
 * В конце сообщается количество обработанных файлов, общее количество строк и количество найденных подстрок,
 * а также сами строки, где содержится искомая подстрока
 *
 * Также реализован наблюдатель за буфером-очередью, где хранятся строки. Он показывает размер очереди, а в конце
 *  - средний размер очереди
 */
public class Main {

    private static final String DIR_NAME = "D:\\Temp\\testdir"; //каталог с текстовыми файлами
    private static final ConcurrentLinkedQueue<File> LIST_OF_FILES = new ConcurrentLinkedQueue<>();
    private static final String STRING_FOR_SEARCH = "джава";    //строка для поиска
    private static final int AMOUNT_OF_THREADS = 10;            //общее количество потоков (исключая main)

    private volatile static Integer amountFindedString = 0;     //число найденных строк

    public synchronized static void amountFindedStringIncrement() {
        amountFindedString++;
    }

    private volatile static int countFiles = 0;     //количество обработанных файлов
    private volatile static int countString = 0;    //количество обработанных строк

    public synchronized static void countFilesIncrement() {
        countFiles++;
    }

    public synchronized static void countStringIncrement() {
        countString++;
    }

    private static ArrayList<String> resultStrings = new ArrayList<>();

    public static void addStringInResultList(String str) {
        synchronized (resultStrings) {
            resultStrings.add(str);
        }
    }

    public static void main(String[] args) {

        File dir = new File(DIR_NAME);

        File[] files = dir.listFiles();

        LIST_OF_FILES.addAll(Arrays.asList(files)); //получаем потокозащищенный список файлов для обработки

        //создаем экзекьютора
        ExecutorService executorService = Executors.newFixedThreadPool(AMOUNT_OF_THREADS);

        int amountConsumers = AMOUNT_OF_THREADS / 4;                 //количество потоков, ищущих подстроку
        int amountProducers = AMOUNT_OF_THREADS - amountConsumers;   //количество потоков, читающих построчно файл

        long timeStart = System.currentTimeMillis();

        //запускаем производителей
        for (int i = 0; i < amountProducers; i++) {
            executorService.submit(new Producer(LIST_OF_FILES));
        }

        //запускаем производителей
        for (int i = 0; i < amountConsumers; i++) {
            executorService.submit(new Consumer(LIST_OF_FILES, STRING_FOR_SEARCH));
        }

        //ради интереса создаем наблюдателя за буфером-очередью
        BufferObserver bufferObserver = new BufferObserver();
        bufferObserver.start();

        executorService.shutdown(); //ждем завершения всех задач в экзекьютере

        while (true) {

            if (executorService.isTerminated()) {
                break;
            }
        }

        long timeFinish = System.currentTimeMillis();

        bufferObserver.interrupt();  //запершаем работу наблюдателя за буфером-очередью
        try {
            bufferObserver.join();  //дожидаемся его отчета
        } catch (InterruptedException ignore) {
            /*NOP*/
        }

        long delta = timeFinish - timeStart;

        System.out.println();
        System.out.println("Разбор файлов завершен. Затрачено времени: " + delta + " мс");
        System.out.println("Обработано файлов: " + countFiles + ", обработано строк: " + countString);
        System.out.println("Найдено подстрок \"" + STRING_FOR_SEARCH + "\": " + amountFindedString);

        System.out.println("Результат:");
        for (String str : resultStrings) {
            System.out.println(str);
        }


    }
}
