package ru.java2.lesson_5_5_NetChat.exceptions;

/**
 * Created by Abilis on 04.05.2016.
 */
public class NoSuchUserException extends NetworkChatException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
