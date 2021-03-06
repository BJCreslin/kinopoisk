package ru.bjcreslin.kinopoisk_console.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bjcreslin.kinopoisk_console.model.Movie;
import ru.bjcreslin.kinopoisk_console.model.MovieRatingPK;
import ru.bjcreslin.kinopoisk_console.model.Rating;

import java.time.LocalDate;

@Repository
public interface RatingRepository extends JpaRepository<Rating, MovieRatingPK> {

  Rating findByMovieRatingPKMovieAndMovieRatingPKDate(Movie movie, LocalDate date);
}