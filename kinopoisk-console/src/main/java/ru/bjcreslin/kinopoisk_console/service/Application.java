package ru.bjcreslin.kinopoisk_console.service;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.bjcreslin.kinopoisk_console.service.impl.KinopoiskHtmlParser;

import java.util.List;

@Component
public class Application implements CommandLineRunner {

    private final RatingProvider provider;

    private final RatingProvider alternateProvider;

    private final KinopoiskHtmlParser kinoParser;

    private final List<Conclusion> resulteres;

    @Value("${html.file.key:file}")
    private String argKey;

    public Application(@Qualifier("webProvider") RatingProvider provider, @Qualifier("fileProvider") RatingProvider alternateProvider, KinopoiskHtmlParser kinoParser, List<Conclusion> resulteres) {
        this.provider = provider;
        this.alternateProvider = alternateProvider;
        this.kinoParser = kinoParser;
        this.resulteres = resulteres;
    }

    @Override
    public void run(String... args) {
        try {
            Element htmlBody;
            if (args.length > 0 && args[0].equals(argKey)) {
                htmlBody = alternateProvider.getHtmlBody();
            } else {
                htmlBody = provider.getHtmlBody();
            }
            var movies = kinoParser.getMoviesWithRating(htmlBody);
            resulteres.forEach(x -> x.output(movies));
        } catch (Exception ex) {
            //
        }
    }
}
