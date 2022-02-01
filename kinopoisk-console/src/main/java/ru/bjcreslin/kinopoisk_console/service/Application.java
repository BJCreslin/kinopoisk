package ru.bjcreslin.kinopoisk_console.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.bjcreslin.kinopoisk_console.service.impl.KinoParser;

import java.util.List;

@Component
public class Application implements CommandLineRunner {

    private final RatingProvider provider;
    private final KinoParser kinoParser;

    private final List<Conclusion> resulteres;

    public Application(@Qualifier("fileProvider") RatingProvider provider, KinoParser kinoParser, List<Conclusion> resulteres) {
        this.provider = provider;
        this.kinoParser = kinoParser;
        this.resulteres = resulteres;
    }

    @Override
    public void run(String... args) {
        var elemenet = provider.getHtmlBody();
        var movies = kinoParser.getMoviesWithRating(elemenet);
        resulteres.forEach(x -> x.output(movies));
    }
}
