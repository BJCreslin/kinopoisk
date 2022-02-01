package ru.bjcreslin.kinopoisk_console.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException;
import ru.bjcreslin.kinopoisk_console.model.Movie;
import ru.bjcreslin.kinopoisk_console.repository.MovieRepository;
import ru.bjcreslin.kinopoisk_console.service.Conclusion;

import java.util.List;

import static ru.bjcreslin.kinopoisk_console.exceptions.SaveDbException.DB_SAVE_ERROR;

@Service("dbaseConclusion")
public class DbaseConclusion implements Conclusion {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbaseConclusion.class);

    public static final String NEW_MOVIE_HAD_BEING_SAVED = "New Movie {} has been saved.";

    private static final String MOVIE_RATING_HAD_BEING_SAVED = "Movie rating for {} has been saved.";


    public static final String OBJECTS_WERE_SAVED = "{} objects were saved";

    private final MovieRepository movieRepository;

    public DbaseConclusion(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void output(List<Movie> movieList) {
        for (Movie movie : movieList) {
            try {
                var optionalMovie = movieRepository.findMovieByOriginalName(movie.getOriginalName());
                if (optionalMovie.isPresent()) {
                    var movie1 = optionalMovie.get();
                    movie1.getRating().addAll(movie.getRating());
                    movieRepository.save(optionalMovie.get());
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(MOVIE_RATING_HAD_BEING_SAVED, movie);
                    }
                } else {
                    movieRepository.save(movie);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(NEW_MOVIE_HAD_BEING_SAVED, movie);
                    }
                }
            } catch (DataAccessException e) {
                LOGGER.error(DB_SAVE_ERROR, e);
                throw new SaveDbException(DB_SAVE_ERROR, e);
            }
        }
        LOGGER.info(OBJECTS_WERE_SAVED, movieList.size());
    }
}
