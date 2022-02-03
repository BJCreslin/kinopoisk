package ru.bjcreslin.kinopoisk_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.kinopoisk_web.model.MovieRatingPK;
import ru.bjcreslin.kinopoisk_web.model.Rating;

import java.time.LocalDate;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, MovieRatingPK> {

    List<Rating> findAllByMovieRatingPK_Date(LocalDate date);

}
