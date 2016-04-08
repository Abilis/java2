package tasks;

/**
 * Created by Abilis on 08.04.2016.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("getSumOfNumbers");
        System.out.println(GetSumOfNumbers.getSumOfNumbers("12 some text 3  7"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers("99 some 1 text -10  7"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers("0 0   -0 some text -1  something else"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers("-0 some some text another text"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers("8.5"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers(null));

        System.out.println();

        System.out.println("CountWord");
        System.out.println(CountWord.countWords("first second   third    fourth\nfiveth"));
        System.out.println(CountWord.countWords(null));

        System.out.println();

        System.out.println("GetClosestToZero");

        System.out.println();

        System.out.println("CountPowerfullNumbers");
        System.out.println(CountPowerfullNumbers.countPowerfulNumbers(1, 10));
        System.out.println(CountPowerfullNumbers.countPowerfulNumbers(1, 1000));
        System.out.println(CountPowerfullNumbers.countPowerfulNumbers(35, 78));
        System.out.println(CountPowerfullNumbers.countPowerfulNumbers(14, 21));


    }

}
