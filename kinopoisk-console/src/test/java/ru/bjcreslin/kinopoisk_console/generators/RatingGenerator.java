package ru.bjcreslin.kinopoisk_console.generators;

import ru.bjcreslin.kinopoisk_console.model.Rating;

import java.util.Random;

public class RatingGenerator {

    public static Rating get() {
        Random random = new Random();
        var rating = new Rating();
        rating.setRatingValue(random.nextDouble());
        rating.setVoters(random.nextInt(10000000));
        rating.setPosition(random.nextInt(10000));
        return rating;
    }
}
