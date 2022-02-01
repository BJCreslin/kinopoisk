package ru.bjcreslin.kinopoisk_console.service.impl;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.exceptions.CannotConnectException;
import ru.bjcreslin.kinopoisk_console.service.Kinopoisk;

import java.io.IOException;

@Service
@PropertySource("classpath:kinopoisk.properties")
public class KinopoiskImpl implements Kinopoisk {
    private static final Logger LOGGER = LoggerFactory.getLogger(KinopoiskImpl.class);

    public static final String CONNECTING_TO = "Connecting to {}";
    public static final String CANNOT_CONNECT = "Cannot connect to %s";

    @Value("${kinopoisk.userAgent:Mozilla}")
    private String userAgent;

    @Value("${kinopoisk.url}")
    private String url;

    @Override
    public void get() {
            LOGGER.info(CONNECTING_TO, url);
        try {
            var doc = Jsoup.connect(url).userAgent(userAgent).get();

            System.out.println(doc.head());
        } catch (IOException e) {
            var message = String.format(CANNOT_CONNECT, url);
            LOGGER.error(message, e);
            throw new CannotConnectException(message, e);
        }
    }

}
