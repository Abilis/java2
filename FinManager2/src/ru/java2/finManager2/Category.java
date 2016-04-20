package ru.java2.finManager2;

/**
 * Created by Abilis on 19.04.2016.
 */
public enum Category {

    HEALTH {
        @Override
        public String toString() {
            return "HEALTH";
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

    OTHER {
        @Override
        public String toString() {
            return "OTHER";
        }
    }

}
