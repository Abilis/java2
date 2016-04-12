package ru.java2.finManager;

/**
 * Created by Abilis on 12.04.2016.
 */
public class Category {

    private String description;

    public String getDescription() {
        return description;
    }

    public Category(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Категория: \"" + description + "\"";
    }
}
