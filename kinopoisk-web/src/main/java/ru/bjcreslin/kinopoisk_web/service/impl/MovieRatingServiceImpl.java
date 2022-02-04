package ru.bjcreslin.kinopoisk_web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_web.model.MovieWithRatingDto;
import ru.bjcreslin.kinopoisk_web.model.Rating;
import ru.bjcreslin.kinopoisk_web.repository.RatingRepository;
import ru.bjcreslin.kinopoisk_web.service.MovieRatingService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.bjcreslin.kinopoisk_web.model.MovieWithRatingDto.CACHE_NAME;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieRatingServiceImpl.class);

    private static final String GETTING_DTO_ON_DATE = "Getting dto on date {}";

    public static final String NO_RESULTS_FOR_THIS_DATE = "No results for this date {}";

    public static final String COUNT_FOR_THIS_DATE = "Found {} data for this date {}";

    private final RatingRepository ratingRepository;

    @Value("${ratings.limit:10}")
    private int ratingsLimit;

    public MovieRatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    @Transactional
    @Cacheable(value = CACHE_NAME, key = "'by_date_' + #date.toString()")
    public List<MovieWithRatingDto> getRatingOnDate(LocalDate date) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(GETTING_DTO_ON_DATE, date.toString());
        }
        var ratings = ratingRepository.findAllByMovieRatingPK_Date(date);
        if (ratings.isEmpty()) {
            LOGGER.info(NO_RESULTS_FOR_THIS_DATE, date);
            return Collections.emptyList();
        }
        var movies = ratings.stream().map(this::toMovieWithRatingDtoConverter).limit(ratingsLimit).collect(Collectors.toList());
        LOGGER.info(COUNT_FOR_THIS_DATE, movies.size(), date);
        return movies;
    }

    protected MovieWithRatingDto toMovieWithRatingDtoConverter(Rating rating) {
        var movie = rating.getMovieRatingPK().getMovie();
        return new MovieWithRatingDto(
                movie.getName(),
                movie.getOriginalName(),
                movie.getYear(),
                rating.getPosition(),
                rating.getVoters(),
                rating.getRatingValue(),
                rating.getMovieRatingPK().getDate()
        );
    }
}
