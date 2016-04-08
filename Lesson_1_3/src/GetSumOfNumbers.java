
/**
 * Created by Сергей on 27.03.2016.
 */
public class GetSumOfNumbers {


        public static int getSumOfNumbers(String s) {
        /*
          Please implement this method to
          return the sum of all integers found in the parameter String. You can assume that
          integers are separated from other parts with one or more spaces (' ' symbol).
          For example, s="12 some text 3  7", result: 22 (12+3+7=22)
         */
            if (s == null) {
                return -1;
            }

            //сплитим строку в массив по пробелам
            String[] strAsArr = s.split(" ");
            int sum = 0;
            int currentNumber;

            //делаем трим каждого элемента массива

            //пробуем парсить элемент в интеджер. Если не получилось - элемент не число. Если получилось - добавляем к сумме

            for (int i = 0; i < strAsArr.length; i++) {

                String elem = strAsArr[i].trim();

                try {
                    currentNumber = Integer.parseInt(elem);
                } catch (Exception e) {
                    continue;
                }

                sum += currentNumber;

            }

            return sum;
        }
    }


