package ru.bjcreslin.kinopoisk_web.ObjGenerators;


import ru.bjcreslin.kinopoisk_web.model.MovieRatingPK;
import ru.bjcreslin.kinopoisk_web.model.Rating;

import java.util.Random;

public class RatingGenerator {

    public static Rating get() {
        Random random = new Random();
        var rating = new Rating();
        rating.setRatingValue(random.nextDouble());
        rating.setVoters(random.nextInt(10000000));
        rating.setPosition(random.nextInt(10000));
        rating.setMovieRatingPK(new MovieRatingPK(MovieGenerator.get()));
        return rating;
    }
}
