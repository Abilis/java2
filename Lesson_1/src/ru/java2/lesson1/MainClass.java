package ru.java2.lesson1;

import java.util.ArrayList;

/**
 * Created by Abilis on 03.04.2016.
 */
public class MainClass {

    public static void main(String[] args) throws StackException {

        GenericStack genericStack = new GenericStack(10);

        System.out.println("Стек пуст, должно быть true: " + genericStack.isEmpty());
        System.out.println("Стек не полон, должно быть false: " + genericStack.isFull());

        genericStack.push(1);
        genericStack.push(2);
        genericStack.push(3);

        int x = (int) genericStack.peek();
        System.out.println("Должен быть \"верхний\" элемент, т.е. 3: " + x);

        System.out.println("Размер должен быть 10: " + genericStack.getSize());

        int y = (int) genericStack.pop();

        System.out.println("Должен быть \"верхний\" элемент, т.е. 3: " + y);

        System.out.println("Размер должен остаться 10: " + genericStack.getSize());

        int a2 = (int) genericStack.pop();
        System.out.println("Должно быть 2: " + a2);

        int a3 = (int) genericStack.pop();
        System.out.println("Должно быть 1: " + a3);
        System.out.println();

        System.out.println("Теперь стек должен быть пуст: " + genericStack.isEmpty());

        try {
            int a4 = (int) genericStack.pop();
        } catch (StackException e) {
            System.out.println("Возникло исключение: мы попытались взять из пустого стека");
         //   e.printStackTrace();
        }

        System.out.println();
        //заполняем стек числами по порядку
        int i = 1;
        while (!genericStack.isFull()) {
            genericStack.push(i++);
        }

        System.out.println("Стек должен быть полон: " + genericStack.isFull());

        try {
            genericStack.push(100);
        } catch (StackException e) {
            System.out.println("Возникло исключение: мы попытались записать в полный стек");
       //     e.printStackTrace();
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        genericStack.popAll(list);

        System.out.println();
        System.out.println("Теперь стек должен быть пуст: " + genericStack.isEmpty());

        System.out.println(list.toString());
        System.out.println();

        //закидываем полученные значения обратно в стек
        genericStack.pushAll(list);
        System.out.println("Теперь стек должен быть полон: " + genericStack.isFull());

        while (!genericStack.isEmpty()) {
            System.out.print(genericStack.pop() + " ");
        }

    }
}
