package ru.bjcreslin.kinopoisk_console.model;

public class MovieWithRatingDto {
    private final String name;
    private final String originalName;
    private final String year;
    private final Integer position;
    private final Integer voters;
    private final Double ratingValue;

    public MovieWithRatingDto(String name, String originalName, String year, Integer position, Integer voters, Double ratingValue) {
        this.name = name;
        this.originalName = originalName;
        this.year = year;
        this.position = position;
        this.voters = voters;
        this.ratingValue = ratingValue;
    }
}
