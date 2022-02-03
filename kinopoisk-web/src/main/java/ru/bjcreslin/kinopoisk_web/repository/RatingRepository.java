package ru.bjcreslin.kinopoisk_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bjcreslin.kinopoisk_web.model.MovieRatingPK;
import ru.bjcreslin.kinopoisk_web.model.Rating;

import java.time.LocalDate;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, MovieRatingPK> {
    List<Rating> findAllByMovieRatingPK_Date(LocalDate date);

    @Query("SELECT u FROM Rating u WHERE u.movieRatingPK.date = date ORDER BY position")
    List<Rating> findAllByDate(LocalDate date);
}
