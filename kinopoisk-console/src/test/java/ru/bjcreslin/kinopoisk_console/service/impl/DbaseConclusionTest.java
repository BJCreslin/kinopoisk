package ru.bjcreslin.kinopoisk_console.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException;
import ru.bjcreslin.kinopoisk_console.model.Movie;
import ru.bjcreslin.kinopoisk_console.repository.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException.DB_SAVE_ERROR;

@DataJpaTest
@DisplayName("DbaseConclusion")
class DbaseConclusionTest {

    private final MovieRepository movieRepository;

    private final DbaseConclusion dbaseConclusion;

    public DbaseConclusionTest() {
        movieRepository = Mockito.mock(MovieRepository.class);
        when(movieRepository.findMovieByOriginalName(anyString())).thenReturn(Optional.of(getMovie()));
        when(movieRepository.save(any())).thenReturn(getMovie());
        dbaseConclusion = new DbaseConclusion(movieRepository);
    }

    @Test
    void shouldSaveAll() {
        var movies = List.of(getMovie(), getMovie());
        dbaseConclusion.output(movies);
        verify(movieRepository, times(movies.size())).findMovieByOriginalName(anyString());
        verify(movieRepository, times(movies.size())).save(any());
    }

    @Test
    void shouldException() {
        var movies = List.of(getMovie(), getMovie());
        when(movieRepository.save(any())).thenThrow(new SaveDbException(DB_SAVE_ERROR, new RuntimeException()));
        Exception exception = assertThrows(SaveDbException.class, () -> dbaseConclusion.output(movies));
        assertTrue(exception.getMessage().contains(DB_SAVE_ERROR));
    }

    private Movie getMovie() {
        var random = new Random();
        var movie = new Movie();
        movie.setName("Name" + random.nextInt());
        movie.setOriginalName("OriginalNAme" + random.nextInt());
        return movie;
    }
}