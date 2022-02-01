package ru.bjcreslin.kinopoisk_console.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bjcreslin.kinopoisk_console.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}