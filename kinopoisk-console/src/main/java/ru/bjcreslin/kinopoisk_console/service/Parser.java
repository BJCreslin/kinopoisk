package ru.bjcreslin.kinopoisk_console.service;

import org.jsoup.nodes.Document;
import ru.bjcreslin.kinopoisk_console.model.Movie;
import ru.bjcreslin.kinopoisk_console.model.Rating;

public interface Parser {
    Movie getMovie(Document document);
    Rating getRating(Document documnet);
}
