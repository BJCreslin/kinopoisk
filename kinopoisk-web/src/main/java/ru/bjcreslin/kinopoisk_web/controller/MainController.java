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
import ru.bjcreslin.kinopoisk_web.service.impl.MovieRatingImpl;

import java.time.LocalDate;

@Controller
@RequestMapping(MainController.MAIN_CONTROLLER_URL)
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    public static final String MAIN_CONTROLLER_URL = "";

    public static final String INDEX = "index";

    private static final String DATE_FOR_RATING = "rating_date";

    private final MovieRatingImpl movieRating;

    public MainController(MovieRatingImpl movieRating) {
        this.movieRating = movieRating;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        var ratingDay = new DateDto(LocalDate.now());
        model.addAttribute(DATE_FOR_RATING, ratingDay);
        var ratings = movieRating.getRatingOnDate(ratingDay.getDate());
        model.addAttribute("ratings", ratings);
        return INDEX;
    }


    @PostMapping("/")
    public String getIndexPostDate(Model model, @ModelAttribute(DATE_FOR_RATING) DateDto ratingDay, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.error("error date {}", ratingDay.getDate());
            ratingDay = new DateDto(LocalDate.now());

        }
        model.addAttribute(DATE_FOR_RATING, ratingDay);

        var ratings = movieRating.getRatingOnDate(ratingDay.getDate());

        model.addAttribute("ratings", ratings);

        return INDEX;
    }
}
