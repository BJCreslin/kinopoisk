package ru.bjcreslin.kinopoisk_console.service.impl;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.exceptions.HtmlParsingFailed;
import ru.bjcreslin.kinopoisk_console.model.MovieWithRatingDto;

import java.util.List;
import java.util.stream.Collectors;

import static ru.bjcreslin.kinopoisk_console.exceptions.HtmlParsingFailed.PARSING_ERROR;

@Service
public class KinoParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(KinoParser.class);

    public static final String PARSING_MOVIE = "Parsing movie: {}";

    public static final String NAME_AND_YEAR_DELIMITER = ",";

    public static final String CLASS_FOR_MOVIES = "desktop-rating-selection-film-item";

    public static final String CLASS_FOR_ORIGINAL_NAME = "selection-film-item-meta__original-name";

    public static final String CLASS_FOR_NAME = "selection-film-item-meta__name";

    public static final String CLASS_FOR_YEAR = CLASS_FOR_ORIGINAL_NAME;

    public static final String CLASS_FOR_POSITION = "film-item-rating-position__position";

    public static final String CLASS_FOR_COUNT = "rating__count";

    public List<MovieWithRatingDto> getMoviesWithRating(Element element) {
        return element.getElementsByClass(CLASS_FOR_MOVIES).stream().map(this::getMovie).collect(Collectors.toList());
    }

    public MovieWithRatingDto getMovie(Element element) {
        try {
            var movie = new MovieWithRatingDto(
                    getName(element),
                    getOriginalName(element),
                    getYear(element),
                    getPosition(element),
                    getVoters(element),
                    getRatingValue(element)
            );
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(PARSING_MOVIE, movie);
            }
            return movie;
        } catch (NullPointerException exception) {
            LOGGER.error(PARSING_ERROR);
            throw new HtmlParsingFailed(PARSING_ERROR, exception);
        }
    }

    protected double getRatingValue(Element element) {
        return Double.parseDouble(element.getElementsByClass("rating__value_positive").first().text());
    }

    protected int getVoters(Element element) {
        return Integer.parseInt(element.getElementsByClass(CLASS_FOR_COUNT).first().text().replace(" ", "").strip());
    }

    protected int getPosition(Element element) {
        return Integer.parseInt(element.getElementsByClass(CLASS_FOR_POSITION).first().text());
    }

    protected String getOriginalName(Element element) {
        var originalName = element.getElementsByClass(CLASS_FOR_ORIGINAL_NAME).first().text();
        if (originalName.trim().matches("[0-9]")) {
            originalName = getName(element);
        } else {
            originalName = originalName.trim().split(NAME_AND_YEAR_DELIMITER)[0].trim();
        }
        return originalName;
    }

    protected String getName(Element element) {
        return element.getElementsByClass(CLASS_FOR_NAME).first().text();
    }

    protected String getYear(Element element) {
        var lineWithYear = element.getElementsByClass(CLASS_FOR_YEAR).first().text();
        return lineWithYear.trim().substring(lineWithYear.length() - 4);
    }
}
