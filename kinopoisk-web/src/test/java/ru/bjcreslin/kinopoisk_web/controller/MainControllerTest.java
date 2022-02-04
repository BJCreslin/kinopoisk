package ru.bjcreslin.kinopoisk_web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.bjcreslin.kinopoisk_web.service.MovieRatingService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.bjcreslin.kinopoisk_web.controller.MainController.ATTRIBUTE_NAME;

@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieRatingService movieRatingService;

    @Test
    void shouldGet() throws Exception {
        var movies = List.of(MovieWithRatingDtoGenerator.get());
        when(movieRatingService.getRatingOnDate(any())).thenReturn(movies);
        mvc.perform(MockMvcRequestBuilders.get(MainController.MAIN_CONTROLLER_URL + MainController.ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ATTRIBUTE_NAME, movies));
    }

    @Test
    void shouldPost() throws Exception {
        var movies = List.of(MovieWithRatingDtoGenerator.get());
        when(movieRatingService.getRatingOnDate(any())).thenReturn(movies);
        mvc.perform(MockMvcRequestBuilders.post(MainController.MAIN_CONTROLLER_URL + MainController.ENDPOINT_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(model().attribute(ATTRIBUTE_NAME, movies));
    }
}