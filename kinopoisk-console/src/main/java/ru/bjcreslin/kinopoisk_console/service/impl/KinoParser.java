package ru.bjcreslin.kinopoisk_console.service.impl;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.model.Movie;
import ru.bjcreslin.kinopoisk_console.model.Rating;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KinoParser {

    public List<Movie> getMoviesWithRating(Element element) {
        System.out.println("******" + element.getElementsByClass("desktop-rating-selection-film-item").size());
        return element.getElementsByClass("desktop-rating-selection-film-item").stream().map(this::getMovie).collect(Collectors.toList());
    }

    public Rating getRating(Element element) {
        Rating rating = new Rating();
        rating.setPosition(Integer.parseInt(element.getElementsByClass("film-item-rating-position__position").first().text()));
        rating.setRatingValue(Double.parseDouble(element.getElementsByClass("rating__value_positive").first().text()));
        rating.setVoters(Integer.parseInt(element.getElementsByClass("rating__count").first().text().replace(" ", "").strip()));
        return rating;
    }

    public Movie getMovie(Element element) {
        Movie movie = new Movie();
        movie.setName(element.getElementsByClass("selection-film-item-meta__name").first().text());
        movie.setOriginalName(element.getElementsByClass("selection-film-item-meta__original-name").first().text());
        movie.setRating(Set.of(getRating(element)));
        System.out.println(movie);
        return movie;
    }


}
