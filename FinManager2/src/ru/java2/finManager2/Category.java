package ru.java2.finManager2;

/**
 * Created by Abilis on 19.04.2016.
 */
public enum Category {

    OTHER {
        @Override
        public String toString() {
            return "OTHER";
        }
    },

    FOOD {
        @Override
        public String toString() {
            return "FOOD";
        }
    },

    CLOTHES {
        @Override
        public String toString() {
            return "CLOTHES";
        }
    },

    TRAVELLING {
        @Override
        public String toString() {
            return "TRAVELLING";
        }
    },

    TEST {
        @Override
        public String toString() {
            return "TEST";
        }
    },

    HEALTH {
        @Override
        public String toString() {
            return "HEALTH";
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
}


