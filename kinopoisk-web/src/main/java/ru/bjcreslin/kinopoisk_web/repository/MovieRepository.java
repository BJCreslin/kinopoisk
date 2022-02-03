package ru.bjcreslin.kinopoisk_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.kinopoisk_web.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}