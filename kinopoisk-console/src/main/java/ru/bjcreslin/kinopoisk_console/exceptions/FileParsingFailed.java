package ru.bjcreslin.kinopoisk_console.exceptions;

public class FileParsingFailed extends RuntimeException {

    public static final String ERROR_PARSING_FROM_FILE = "Error parsing from file";

    public FileParsingFailed(String message, Exception e) {
        super(message, e);
    }
}
