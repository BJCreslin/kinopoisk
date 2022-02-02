package ru.bjcreslin.kinopoisk_console.generators;

import ru.bjcreslin.kinopoisk_console.model.MovieWithRatingDto;

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
                random.nextDouble()
        );
    }
}
