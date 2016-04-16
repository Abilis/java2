package tasks;

/**
 * Created by Abilis on 16.04.2016.
 */
public class Main {


    public static void main(String[] args) {

        //Проверка метода reverseArray

        //создаем тестовые массивы
        Integer[] arrNumbers1 = new Integer[10];
        Integer[] arrNumbers2 = new Integer[11];
        Integer[] arrEmpty = new Integer[0];
        Integer[] arrNull = null;

        //заполняем тестовые массивы
        for (int i = 0; i < arrNumbers1.length; i++) {
            arrNumbers1[i] = (i + 1);
        }

        for (int i = 0; i < arrNumbers2.length; i++) {
            arrNumbers2[i] = (i + 1);
        }


        Object[] reversedArrNumber1 = ReverseArray.reverseArray(arrNumbers1);
        Object[] reversedArrNumber2 = ReverseArray.reverseArray(arrNumbers2);
        Object[] reversedArrEmpty = ReverseArray.reverseArray(arrEmpty);
        Object[] reversedArrNull = ReverseArray.reverseArray(arrNull);


        //распечатываем исходный и перевернутый массивы
        for (int i = 0; i < arrNumbers1.length; i++) {
            System.out.print(arrNumbers1[i] + " ");
        }
        System.out.println();


        for (int i = 0; i < reversedArrNumber1.length; i++) {
            Integer integerI = (Integer) reversedArrNumber1[i];
            System.out.print(integerI + " ");
        }
        System.out.println();
        System.out.println();

        //второй массив интеджеров
        for (int i = 0; i < arrNumbers2.length; i++) {
            System.out.print(arrNumbers2[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < reversedArrNumber2.length; i++) {
            Integer integerI = (Integer) reversedArrNumber2[i];
            System.out.print(integerI + " ");
        }
        System.out.println();

        for (int i = 0; i < reversedArrEmpty.length; i++) {
            Integer integerI = (Integer) reversedArrEmpty[i];
            System.out.print(integerI + " ");
        }
        System.out.println();
        System.out.println();

        //Проверка с массивами строк
        String[] stringArr = new String[5];
        stringArr[0] = "first";
        stringArr[1] = "second";
        stringArr[2] = "third";
        stringArr[3] = "fourth";
        stringArr[4] = "fiveth";

        Object[] reversedStrArr = ReverseArray.reverseArray(stringArr);

        //печатаем исходный массив
        for (int i = 0; i < stringArr.length; i++) {
            System.out.print(stringArr[i] + " ");
        }
        System.out.println();

        //печатаем перевернутый массив
        for (int i = 0; i < reversedStrArr.length; i++) {
            String strI = (String) reversedStrArr[i];
            System.out.print(strI + " ");
        }
        System.out.println();
        System.out.println();


        //проверка методы isPalindrome
        System.out.println(IsPalindrome.isPalindrome("abcba"));
        System.out.println(IsPalindrome.isPalindrome("abcdddcba"));
        System.out.println(IsPalindrome.isPalindrome("abab"));
        System.out.println(IsPalindrome.isPalindrome("abba"));
        System.out.println(IsPalindrome.isPalindrome("abcd"));

        System.out.println();
        System.out.println();

        //проверка метода getCountOfOnes
        System.out.println(getCountOfOnes.getCountOfOnes(2)); //10
        System.out.println(getCountOfOnes.getCountOfOnes(6)); //110
        System.out.println(getCountOfOnes.getCountOfOnes(10)); //1010
        System.out.println(getCountOfOnes.getCountOfOnes(111)); //1101111


    }

}
