package ru.bjcreslin.kinopoisk_console.service.impl;

import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.model.Movie;
import ru.bjcreslin.kinopoisk_console.service.Conclusion;

import java.util.List;

@Service ("consoleConclusion")
public class ConsoleConclusion implements Conclusion {

    @Override
    public void output(List<Movie> movieList) {
        movieList.forEach(System.out::println);
    }
}
