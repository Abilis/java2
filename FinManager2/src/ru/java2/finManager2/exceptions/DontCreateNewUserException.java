package ru.java2.finManager2.exceptions;

import java.sql.SQLException;

/**
 * Created by Abilis on 20.04.2016.
 */
public class DontCreateNewUserException extends SQLException {

    public DontCreateNewUserException(String reason) {
        super(reason);
    }
}
