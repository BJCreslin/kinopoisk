package ru.bjcreslin.kinopoisk_web.service;



import ru.bjcreslin.dto.MovieWithRatingDto;

import java.time.LocalDate;
import java.util.List;

public interface MovieRating {

    List<MovieWithRatingDto> getRatingOnDate(LocalDate date);
}
