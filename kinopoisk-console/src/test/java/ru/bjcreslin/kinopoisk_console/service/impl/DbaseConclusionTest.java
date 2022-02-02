package ru.bjcreslin.kinopoisk_console.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException;
import ru.bjcreslin.kinopoisk_console.generators.MovieGenerator;
import ru.bjcreslin.kinopoisk_console.generators.MovieWithRatingDtoGenerator;
import ru.bjcreslin.kinopoisk_console.generators.RatingGenerator;
import ru.bjcreslin.kinopoisk_console.repository.MovieRepository;
import ru.bjcreslin.kinopoisk_console.repository.RatingRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException.DB_SAVE_ERROR;

@DataJpaTest
@DisplayName("DbaseConclusion")
class DbaseConclusionTest {

    private final MovieRepository movieRepository;

    private final RatingRepository ratingRepository;

    private final DbaseConclusion dbaseConclusion;

    public DbaseConclusionTest() {
        movieRepository = Mockito.mock(MovieRepository.class);
        when(movieRepository.findMovieByOriginalName(anyString())).thenReturn(MovieGenerator.get());
        when(movieRepository.save(any())).thenReturn(MovieGenerator.get());

        ratingRepository = Mockito.mock(RatingRepository.class);
        when(ratingRepository.save(any())).thenReturn(RatingGenerator.get());

        dbaseConclusion = new DbaseConclusion(ratingRepository, movieRepository);
    }

    @Test
    void shouldSaveAll() {
        var movies = List.of(MovieWithRatingDtoGenerator.get(), MovieWithRatingDtoGenerator.get());
        dbaseConclusion.output(movies);
        verify(movieRepository, times(movies.size())).findMovieByOriginalName(anyString());
        verify(movieRepository, times(movies.size())).save(any());
        verify(ratingRepository, times(movies.size())).save(any());
    }

    @Test
    void shouldException() {
        var movies = List.of(MovieWithRatingDtoGenerator.get(), MovieWithRatingDtoGenerator.get());
        when(movieRepository.save(any())).thenThrow(new SaveDbException(DB_SAVE_ERROR, new RuntimeException()));
        Exception exception = assertThrows(SaveDbException.class, () -> dbaseConclusion.output(movies));
        assertTrue(exception.getMessage().contains(DB_SAVE_ERROR));
    }
}