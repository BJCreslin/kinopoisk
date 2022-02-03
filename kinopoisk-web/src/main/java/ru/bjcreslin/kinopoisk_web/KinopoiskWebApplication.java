package ru.bjcreslin.kinopoisk_web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KinopoiskWebApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(KinopoiskWebApplication.class);

    public static void main(String[] args) {
        LOGGER.info("STARTING THE APPLICATION");
        SpringApplication.run(KinopoiskWebApplication.class, args);
    }
}
