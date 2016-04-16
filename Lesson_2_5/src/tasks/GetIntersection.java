package tasks;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Сергей on 16.04.2016.
 */
public class GetIntersection {

    public static Set<Object> getIntersection(Set<Object> a, Set<Object> b) {
        /*
          Please implement this method to
          return a Set equal to the intersection of the parameter Sets
          The method should not chage the content of the parameters.
         */

        //проверка на null
        if (a == null || b == null) {
            return null;
        }

        //создаем новое множество, которое и будем возвращать
        Set<Object> intersectionSet = new HashSet<Object>();

        //проходим по меньшему полученному множеству и смотрим, есть ли данный элемент во втором множестве
        //если да - записываем его в новое множество

        int sizeOfA = a.size();
        int sizeOfB = b.size();

        if (sizeOfA <= sizeOfB) { //переданное множество a меньше или равно множеству b

            for (Object o : a) { //проходим по всем элементам множества а
                if (b.contains(o)) {    //если текущий элемент содержится в множестве b
                    intersectionSet.add(o); //то кладем его в множество пересечений
                }
            }

        }
        else { //переданное множество b меньше множеству a

            for (Object o : b) { //проходим по всем элементам множества b
                if (a.contains(o)) { //если текущий элемент содержится в множестве a
                    intersectionSet.add(o);
                }
            }

        }

        return intersectionSet;

    }
}
