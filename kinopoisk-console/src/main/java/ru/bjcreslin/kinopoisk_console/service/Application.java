package ru.bjcreslin.kinopoisk_console.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.bjcreslin.kinopoisk_console.service.impl.KinoParser;

@Component
public class Application implements CommandLineRunner {

    private final RatingProvider provider;
    private final KinoParser kinoParser;

    public Application(@Qualifier("fileProvider") RatingProvider  provider, ru.bjcreslin.kinopoisk_console.service.impl.KinoParser kinoParser) {
        this.provider = provider;
        this.kinoParser = kinoParser;
    }

    @Override
    public void run(String... args) throws Exception {
        var elemenet = provider.get();
        System.out.println(elemenet.html());
        var movies = kinoParser.getMoviesWithRating(elemenet);
        System.out.println("NUMBER " + movies.size());
        movies.stream().forEach(System.out::println);
    }
}
