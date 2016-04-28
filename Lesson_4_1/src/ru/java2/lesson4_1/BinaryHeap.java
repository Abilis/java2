package ru.java2.lesson4_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abilis on 26.04.2016.
 */
public class BinaryHeap {

    private List<Integer> list;
    private int heapSize;

    public int getHeapSize() {
        return heapSize;
    }

    //инициализируем кучу. Создаем новый аррей-лист размером равным размеру принятого списка
    public BinaryHeap(List<Integer> list) {
        heapSize = list.size();
        this.list = new ArrayList<>(heapSize);

        //добавляем все элементы полученного списка в кучу
        this.list.addAll(list);

        //и перестраиваем дерево для каждой вершины
        for (int i = heapSize / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    //метод добавляет новый элемент в кучу
    public void add(int value) {

        list.add(value);
        heapSize++;
        int i = heapSize - 1; //индекс только что добавленного элемента
        int parent = (i - 1) / 2; //индекс родителя только что добавленного элемента

        //проталкиваем добавленный элемент к корню
        while (i > 0 && list.get(i) > list.get(parent)) {

            //меняем элемент и его родителя местами
            int temp = list.get(i);
            list.set(i, list.get(parent));
            list.set(parent, temp);

            //определяем заново индексы только что добавленного элемента и его родителя
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    //метод упорядочивает кучу для дерева с корнем в вершине i
    public void heapify(int i) {

        int leftChild;
        int rightChild;
        int largestChild;

        while (true) {

            //определяем текущую вершину и его потомков
            largestChild = i;
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;

            //проверка левого потомка. Если он больше родителя, то индексом родителя становится индекс левого потомка
            if (leftChild < heapSize && list.get(leftChild) > list.get(largestChild)) {
                largestChild = leftChild;
            }

            //проверка правого потомка. Если он больше родителя, то индексом родителя становится индекс правого потомка
            if (rightChild < heapSize && list.get(rightChild) > list.get(largestChild)) {
                largestChild = rightChild;
            }

            //если в результате первых двух проверок оказалось, что ни один из потомков не больше родителя, то завершаем цикл
            if (largestChild == i) {
                break;
            }

            //меняем местами i-ый элемент и тот, который теперь находится под индексом родителя
            int temp = list.get(i);
            list.set(i, list.get(largestChild));
            list.set(largestChild, temp);

            //и определяем индекс нового корня
            i = largestChild;

        }

    }


    //взятие максимального элемента. Он всегда хранится на 0 позиции
    public int getMax() {
        return list.get(0);
    }

    //удаление (извлечение) максимального элемента
    public int removeMax() {

        //меняем местами 0 элемент и последний
        int result = list.get(0);
        list.set(0, list.get(heapSize - 1));
        list.set(heapSize - 1, result);

        //удаляем последний и применяем упорядочивание для корня всего дерева
        list.remove(heapSize - 1);
        heapSize--;

        heapify(0);

        return result;
    }

    //сортировка листа с помощью кучи
    public static void sort(List sourseList) {

        BinaryHeap binaryHeap = new BinaryHeap(sourseList);

        for (int i = sourseList.size() - 1; i >= 0; i--) {
            sourseList.set(i, binaryHeap.removeMax());
        }
    }

    @Override
    public String toString() {
        return "BinaryHeap{" +
                "list=" + list +
                ", heapSize=" + heapSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryHeap that = (BinaryHeap) o;

        if (getHeapSize() != that.getHeapSize()) return false;
        return list != null ? list.equals(that.list) : that.list == null;

    }

    @Override
    public int hashCode() {
        int result = list != null ? list.hashCode() : 0;
        result = 31 * result + getHeapSize();
        return result;
    }
}

