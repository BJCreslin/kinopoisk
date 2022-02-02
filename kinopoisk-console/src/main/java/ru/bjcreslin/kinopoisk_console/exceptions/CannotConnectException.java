package ru.bjcreslin.kinopoisk_console.exceptions;

public class CannotConnectException extends RuntimeException {

    public CannotConnectException(String message, Throwable e) {

        super(message, e);
    }

    public CannotConnectException(String message) {

        super(message);
    }
}
