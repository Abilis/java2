package ru.java2.finManager2;

import java.awt.*;

/**
 * Created by Abilis on 19.04.2016.
 */
public enum Category {

    OTHER {
        @Override
        public String toString() {
            return "OTHER";
        }

        public Color getColor() {
            return Color.CYAN;
        }
    },

    FOOD {
        @Override
        public String toString() {
            return "FOOD";
        }

        public Color getColor() {
            return Color.GREEN;
        }
    },

    CLOTHES {
        @Override
        public String toString() {
            return "CLOTHES";
        }

        public Color getColor() {
            return Color.GRAY;
        }
    },

    TRAVELLING {
        @Override
        public String toString() {
            return "TRAVELLING";
        }

        public Color getColor() {
            return Color.ORANGE;
        }
    },

    TEST {
        @Override
        public String toString() {
            return "TEST";
        }

        public Color getColor() {
            return Color.YELLOW;
        }
    },

    HEALTH {
        @Override
        public String toString() {
            return "HEALTH";
        }

        public Color getColor() {
            return Color.RED;
        }

    };

    public static int getIndex(Category category) {

        Category[] categories = values();

        for (int i = 0; i < categories.length; i++) {
            if (category.equals(categories[i])) {
                return i;
            }
        }

        return 0; //если не нашлась - возвращаем 0 (OTHER)
    }


    public static String[] getArrStrCategories() {

        Category[] categories = values();

        String[] result = new String[categories.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = categories[i].toString();
        }

        return result;
    }

    public Color getColor() {

        Category[] categories = values();
        String[] categoriesStr = getArrStrCategories();

        return null;
    }
}


