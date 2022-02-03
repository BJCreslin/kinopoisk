package ru.bjcreslin.kinopoisk_console.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException;
import ru.bjcreslin.kinopoisk_console.model.Movie;
import ru.bjcreslin.kinopoisk_console.model.MovieRatingPK;
import ru.bjcreslin.kinopoisk_console.model.MovieWithRatingDto;
import ru.bjcreslin.kinopoisk_console.model.Rating;
import ru.bjcreslin.kinopoisk_console.repository.MovieRepository;
import ru.bjcreslin.kinopoisk_console.repository.RatingRepository;
import ru.bjcreslin.kinopoisk_console.service.Conclusion;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException.DB_SAVE_ERROR;

@Service("dbaseConclusion")
public class DbaseConclusion implements Conclusion {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbaseConclusion.class);

    public static final String NEW_MOVIE_HAD_BEING_SAVED = "New Movie {} has been saved.";

    private static final String MOVIE_RATING_HAD_BEING_SAVED = "Movie rating for {} has been saved.";

    public static final String OBJECTS_WERE_SAVED = "{} objects were saved";

    public static final String TODAY = "The rating for the film {} has already been received today";

    private final RatingRepository ratingRepository;

    private final MovieRepository movieRepository;

    public DbaseConclusion(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }


    @Override
    @Transactional
    public void output(List<MovieWithRatingDto> movieList) {
        for (MovieWithRatingDto movieWithRatingDto : movieList) {
            var optionalMovie = movieRepository.findMovieByOriginalName(movieWithRatingDto.getOriginalName());
            if (Objects.nonNull(optionalMovie)) {
                saveToDb(optionalMovie, getRatingFromDto(movieWithRatingDto));
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(MOVIE_RATING_HAD_BEING_SAVED, optionalMovie);
                }
            } else {
                saveNewMovieToDb(getMovieFromDto(movieWithRatingDto), getRatingFromDto(movieWithRatingDto));
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(NEW_MOVIE_HAD_BEING_SAVED, movieWithRatingDto);
                }
            }
        }
        LOGGER.info(OBJECTS_WERE_SAVED, movieList.size());
    }

    protected void saveNewMovieToDb(Movie movie, Rating rating) {
        movie = movieRepository.save(movie);
        var ratRk = new MovieRatingPK(movie);
        rating.setMovieRatingPK(ratRk);
        var rat = ratingRepository.save(rating);
        movie.getRating().add(rat);
    }

    protected void saveToDb(Movie movie, Rating rating) {
        if (ratingRepository.findByMovieRatingPKMovieAndMovieRatingPKDate(movie, LocalDate.now()) == null) {
            try {
                var ratRk = new MovieRatingPK(movie);
                rating.setMovieRatingPK(ratRk);
                movie.getRating().add(rating);
                ratingRepository.save(rating);
                movieRepository.save(movie);
            } catch (DataAccessException e) {
                LOGGER.error(DB_SAVE_ERROR, e);
                throw new SaveDbException(DB_SAVE_ERROR, e);
            }
        } else {
            LOGGER.info(TODAY, movie.getName());
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
