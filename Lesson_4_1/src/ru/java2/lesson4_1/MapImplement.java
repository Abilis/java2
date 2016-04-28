package ru.java2.lesson4_1;


import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Abilis on 28.04.2016.
 */
public class MapImplement {

    interface Operator<R, T> {
        R apply(T t);
    }



    public static Collection map(Collection coll, Operator oper) {

        Collection<Object> result = new ArrayList<>(coll.size());

        for (Object c : coll) {
            result.add(oper.apply(c));
        }


        return result;
    }

}
