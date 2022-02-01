package ru.bjcreslin.kinopoisk_console.service.impl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.exceptions.CannotConnectException;
import ru.bjcreslin.kinopoisk_console.service.RatingProvider;

import java.io.IOException;

@Service("webProvider")
@PropertySource("classpath:kinopoisk.properties")
public class WebProviderImpl implements RatingProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebProviderImpl.class);

    public static final String CONNECTING_TO = "Connecting to {}";

    public static final String CANNOT_CONNECT = "Cannot connect to %s";

    @Value("${kinopoisk.userAgent:Mozilla}")
    private String userAgent;

    @Value("${kinopoisk.url}")
    private String url;

    @Value("${kinopoisk.referrer}")
    private String referrer;

    @Value("${kinopoisk.timeout}")
    private int timeout;

    @Override
    public Element getHtmlBody() {
        LOGGER.info(CONNECTING_TO, url);
        try {
            Connection connection = Jsoup.connect(url);
            connection.userAgent(userAgent);
            connection.timeout(timeout);
            connection.referrer(referrer);
            Document doc = connection.get();
            System.out.println(doc.head().text());
            return doc.body();
        } catch (IOException e) {
            var message = String.format(CANNOT_CONNECT, url);
            LOGGER.error(message, e);
            throw new CannotConnectException(message, e);
        }
    }

}
