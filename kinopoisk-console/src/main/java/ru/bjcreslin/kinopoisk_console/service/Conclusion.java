package ru.bjcreslin.kinopoisk_console.service;

import ru.bjcreslin.kinopoisk_console.model.MovieWithRatingDto;

import java.util.List;

public interface Conclusion {

    void output(List<MovieWithRatingDto> movieList);
}
