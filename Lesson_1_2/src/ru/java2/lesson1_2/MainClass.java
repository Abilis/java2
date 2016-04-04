package ru.java2.lesson1_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



/**
 * Created by Abilis on 04.04.2016.
 */
public class MainClass {

    public static void main(String[] args) {

        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("aaa");
        listOfStrings.add("b");
        listOfStrings.add("cdef");
        listOfStrings.add("cd");
        listOfStrings.add("def");

        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };

        Collections.sort(listOfStrings, lengthComparator);

        System.out.println(listOfStrings);

    }
}
