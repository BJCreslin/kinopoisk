package ru.bjcreslin.kinopoisk_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.kinopoisk_web.service.impl.MovieRatingImpl;

import java.time.LocalDate;
import java.util.Objects;

@Controller
@RequestMapping(MainController.MAIN_CONTROLLER_URL)
public class MainController {

    public static final String MAIN_CONTROLLER_URL = "";

    public static final String INDEX = "index";

    private static final String DATE_FOR_RATING = "rating_date";

    private final MovieRatingImpl movieRating;

    public MainController(MovieRatingImpl movieRating) {
        this.movieRating = movieRating;
    }

    @GetMapping("/")
    public String getIndex(Model model, @ModelAttribute(DATE_FOR_RATING) LocalDate ratingDay, BindingResult result) {

        if (Objects.isNull(ratingDay)) {
            ratingDay = LocalDate.now();
        }
        ratingDay = LocalDate.now();
        model.addAttribute(DATE_FOR_RATING, ratingDay);

        var ratings = movieRating.getRatingOnDate(ratingDay);

        model.addAttribute("ratings", ratings);

        return INDEX;
    }
}
