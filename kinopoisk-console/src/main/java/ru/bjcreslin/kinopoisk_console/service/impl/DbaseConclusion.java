package ru.bjcreslin.kinopoisk_console.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException;
import ru.bjcreslin.kinopoisk_console.model.Movie;
import ru.bjcreslin.kinopoisk_console.model.MovieWithRatingDto;
import ru.bjcreslin.kinopoisk_console.model.Rating;
import ru.bjcreslin.kinopoisk_console.repository.MovieRepository;
import ru.bjcreslin.kinopoisk_console.repository.RatingRepository;
import ru.bjcreslin.kinopoisk_console.service.Conclusion;

import java.util.List;

import static ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException.DB_SAVE_ERROR;

@Service("dbaseConclusion")
public class DbaseConclusion implements Conclusion {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbaseConclusion.class);

    public static final String NEW_MOVIE_HAD_BEING_SAVED = "New Movie {} has been saved.";

    private static final String MOVIE_RATING_HAD_BEING_SAVED = "Movie rating for {} has been saved.";

    public static final String OBJECTS_WERE_SAVED = "{} objects were saved";

    private final RatingRepository ratingRepository;

    private final MovieRepository movieRepository;

    public DbaseConclusion(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void output(List<MovieWithRatingDto> movieList) {
        for (MovieWithRatingDto movieWithRatingDto : movieList) {
            var optionalMovie = movieRepository.findMovieByOriginalName(movieWithRatingDto.getOriginalName());
            if (optionalMovie.isPresent()) {
                saveToDb(optionalMovie.get(), getRatingFromDto(movieWithRatingDto));
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(MOVIE_RATING_HAD_BEING_SAVED, optionalMovie.get());
                }
            } else {
                saveToDb(getMovieFromDto(movieWithRatingDto), getRatingFromDto(movieWithRatingDto));
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(NEW_MOVIE_HAD_BEING_SAVED, movieWithRatingDto);
                }
            }
        }
        LOGGER.info(OBJECTS_WERE_SAVED, movieList.size());
    }

    private void saveToDb(Movie movie, Rating rating) {
        try {
            movie.getRating().add(rating);
            rating.setMovie(movie);
            ratingRepository.save(rating);
            movieRepository.save(movie);
        } catch (DataAccessException e) {
            LOGGER.error(DB_SAVE_ERROR, e);
            throw new SaveDbException(DB_SAVE_ERROR, e);
        }
    }

    protected Rating getRatingFromDto(MovieWithRatingDto movieWithRatingDto) {
        var rating = new Rating();
        BeanUtils.copyProperties(movieWithRatingDto, rating);
        return rating;
    }

    protected Movie getMovieFromDto(MovieWithRatingDto movieWithRatingDto) {
        var movie = new Movie();
        BeanUtils.copyProperties(movieWithRatingDto, movie);
        return movie;
    }
}
