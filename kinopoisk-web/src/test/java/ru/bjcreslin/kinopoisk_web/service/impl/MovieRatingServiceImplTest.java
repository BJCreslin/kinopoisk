package ru.bjcreslin.kinopoisk_web.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.bjcreslin.kinopoisk_web.ObjGenerators.RatingGenerator;
import ru.bjcreslin.kinopoisk_web.repository.RatingRepository;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MovieRatingServiceImplTest {

    private final MovieRatingServiceImpl service;

    private final RatingRepository ratingRepository;

    public MovieRatingServiceImplTest() {
        ratingRepository = Mockito.mock(RatingRepository.class);
        when(ratingRepository.findAllByMovieRatingPK_Date(any())).thenReturn(List.of(RatingGenerator.get(),RatingGenerator.get()));
        service = new MovieRatingServiceImpl(ratingRepository);
    }

    @Test
    void shouldGetRatingOnDate() {
        service.getRatingOnDate(LocalDate.now());
        verify(ratingRepository, times(1)).findAllByMovieRatingPK_Date(any());
    }

    @Test
    void shouldConvert() {
        var rating = RatingGenerator.get();
        var movieWithRating = service.toMovieWithRatingDtoConverter(rating);
        Assertions.assertEquals(rating.getRatingValue(), movieWithRating.getRatingValue(), 0.01);
        Assertions.assertEquals(rating.getPosition(), movieWithRating.getPosition());
        Assertions.assertEquals(rating.getVoters(), movieWithRating.getVoters());
        Assertions.assertEquals(rating.getMovieRatingPK().getMovie().getName(), movieWithRating.getName());
        Assertions.assertEquals(rating.getMovieRatingPK().getMovie().getOriginalName(), movieWithRating.getOriginalName());
    }
}