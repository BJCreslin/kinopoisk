package ru.bjcreslin.kinopoisk_console.exceptions;

public class SaveDbException extends RuntimeException {

    public static final String DB_SAVE_ERROR = "Db save error";

    public SaveDbException(String dbSaveError, Exception e) {
        super(dbSaveError, e);
    }
}
