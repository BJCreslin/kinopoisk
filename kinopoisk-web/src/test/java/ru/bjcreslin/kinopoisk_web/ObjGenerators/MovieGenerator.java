package ru.bjcreslin.kinopoisk_web.ObjGenerators;

import ru.bjcreslin.kinopoisk_web.model.Movie;

import java.util.HashSet;
import java.util.Random;

public class MovieGenerator {

    public static Movie get() {
        var random = new Random();
        var movie = new Movie();
        movie.setName("Name" + random.nextInt());
        movie.setOriginalName("OriginalNAme" + random.nextInt());
        movie.setRating(new HashSet<>());
        return movie;
    }
}
