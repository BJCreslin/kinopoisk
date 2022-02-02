package ru.bjcreslin.kinopoisk_console.exceptions;

public class HtmlParsingFailed extends RuntimeException {

    public static final String PARSING_ERROR = "ERROR Parsing movie from html code;";

    public HtmlParsingFailed(String message, NullPointerException exception) {
        super(message, exception);
    }
}
