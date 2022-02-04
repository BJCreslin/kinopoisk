package ru.bjcreslin.kinopoisk_web.controller;

import ru.bjcreslin.kinopoisk_web.model.MovieWithRatingDto;

import java.time.LocalDate;
import java.util.Random;

public class MovieWithRatingDtoGenerator {
    static public MovieWithRatingDto get() {
        var random = new Random();
        return new MovieWithRatingDto(
                "Name" + random.nextInt(),
                "OriginalName" + random.nextInt(),
                Integer.toString(random.nextInt(2029)),
                random.nextInt(),
                random.nextInt(),
                random.nextDouble(),
                LocalDate.now().minusDays(random.nextInt())
        );
    }
}
