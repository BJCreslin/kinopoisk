package ru.bjcreslin.kinopoisk_console.service.impl;

import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.model.MovieWithRatingDto;
import ru.bjcreslin.kinopoisk_console.service.Conclusion;

import java.util.List;

@Service("consoleConclusion")
public class ConsoleConclusion implements Conclusion {

    @Override
    public void output(List<MovieWithRatingDto> movieList) {
        movieList.forEach(System.out::println);
    }
}
