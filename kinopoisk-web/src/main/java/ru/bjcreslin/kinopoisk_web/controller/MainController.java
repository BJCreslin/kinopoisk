package ru.bjcreslin.kinopoisk_web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.kinopoisk_web.model.DateDto;
import ru.bjcreslin.kinopoisk_web.service.MovieRatingService;

import java.time.LocalDate;

@Controller
@RequestMapping(MainController.MAIN_CONTROLLER_URL)
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    public static final String MAIN_CONTROLLER_URL = "";

    public static final String ENDPOINT_URL = "/";

    public static final String INDEX = "index";

    private static final String DATE_FOR_RATING = "rating_date";

    public static final String ATTRIBUTE_NAME = "ratings";

    private static final String ERROR_DATE = "Error date {}";

    public static final String GETTING_DATA_FOR_A_DATE = "Getting data for a date {}";

    public static final String GET_INDEX_PAGE = "Get index page.";

    private final MovieRatingService movieRatingService;

    public MainController(MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @GetMapping(ENDPOINT_URL)
    public String getIndex(Model model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(GET_INDEX_PAGE);
        }
        var ratingDay = new DateDto(LocalDate.now());
        model.addAttribute(DATE_FOR_RATING, ratingDay);
        var ratings = movieRatingService.getRatingOnDate(ratingDay.getDate());
        model.addAttribute(ATTRIBUTE_NAME, ratings);
        return INDEX;
    }

    @PostMapping("/")
    public String getIndexPostDate(Model model, @ModelAttribute(DATE_FOR_RATING) DateDto ratingDay, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.error(ERROR_DATE, ratingDay.getDate());
            ratingDay = new DateDto(LocalDate.now());
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(GETTING_DATA_FOR_A_DATE, ratingDay);
        }
        model.addAttribute(DATE_FOR_RATING, ratingDay);
        var ratings = movieRatingService.getRatingOnDate(ratingDay.getDate());
        model.addAttribute(ATTRIBUTE_NAME, ratings);
        return INDEX;
    }
}
