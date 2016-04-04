package ru.java2.lesson1;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Abilis on 04.04.2016.
 */
public class GenericStack <E> implements Stack <E> {

    LinkedList<E> stack; //в качестве стека будет выступать такой список. В качестве верхнего элемента будет выступать нулевой элемент
    private int mSize = 10; //максимальный размер стека

    public GenericStack(int mSize) {
        this.mSize = mSize;
        stack = new LinkedList<E>();
    }

    @Override
    public void push(E element) throws StackException {
        if (isFull()) {
            throw new StackException();
        }
        else {
            stack.add(0, element);
        }
    }

    @Override
    public E pop() throws StackException {
        if (isEmpty()) {
            throw new StackException();
        }
        else {
            E elem = stack.get(0); //вытаскиваем "верхний" элемент
            stack.remove(0); //удаляем его
            return elem;
        }
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null; //хм, тут бы логичней кинуть исключение, но в сигнатуре интерфейса нет проброса этого исключения
        }
        else {
            return stack.get(0); //возвращаем верхний элемент без удаления
        }
    }

    @Override
    public int getSize() {
        return mSize;
    }

    @Override
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    @Override
    public boolean isFull() {
        return stack.size() == mSize;
    }

    @Override
    public void pushAll(Collection<? extends E> src) throws StackException {

        int srcSize = src.size(); //размер входной коллекции

        if (stack.size() + srcSize > mSize) { //если в результате манипуляций размер стэка будет больше максимального
            throw new StackException();
        }
        else {
            for (E elem : src) { //добавляем все "наверх", т.е. в начало списка
                stack.add(0, elem);
            }
        }
    }

    @Override
    public void popAll(Collection<? super E> dst) throws StackException {

        for (int i = 0; i < stack.size(); i++) { //перебрасываем из стека в dst "сверху вниз" элементы
            dst.add(stack.get(i));
        }

        stack.clear(); //очищаем стэк
    }
}
