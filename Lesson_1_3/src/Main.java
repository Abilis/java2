/**
 * Created by Abilis on 08.04.2016.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println(GetSumOfNumbers.getSumOfNumbers("12 some text 3  7"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers("99 some 1 text -10  7"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers("0 0   -0 some text -1  something else"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers("-0 some some text another text"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers("8.5"));
        System.out.println(GetSumOfNumbers.getSumOfNumbers(null));

    }

}
