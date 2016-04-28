package ru.java2.lesson4_1.tasks;

import java.util.List;

/**
 * Created by Abilis on 27.04.2016.
 */
public class GetLargestRootToLeafSum {

    // Please do not change this interface
    public static interface Node {
        int getValue();
        List<Node> getChildren();
    }


    public static int getLargestRootToLeafSum(Node root) {
    /*
      A root-to-leaf path in a tree is a path from a leaf node through all its ancestors
      to the root node inclusively.
      A "root-to-leaf sum" is a sum of the node values in a root-to-leaf path.

      Please implement this method to
      return the largest root-to-leaf sum in the tree.
     */

        int path = 0;

        while (!root.getChildren().isEmpty()) { //пока у текущего рута есть потомки

            //получаем список всех потомков текущего узла
            List<Node> childrens = root.getChildren();


            int max = 0; //максимальное значение
            int indexOfMax = 0; //индекс. Будет использоваться для доступа к максимальному значению
            for (int i = 0; i < childrens.size(); i++) { //пробегаем всех потомков
                if (childrens.get(i).getValue() > max) { //находим максимального
                    max = childrens.get(i).getValue();
                    indexOfMax = i;
                }
            }

            //прибавляем к пути максимального потомка
            path += max;

            //и перебрасываем на него ссылку
            root = childrens.get(indexOfMax);
        }




        return path;
    }
}

