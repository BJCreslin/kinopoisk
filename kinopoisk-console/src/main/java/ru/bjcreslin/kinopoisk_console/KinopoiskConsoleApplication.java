package ru.bjcreslin.kinopoisk_console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class KinopoiskConsoleApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(KinopoiskConsoleApplication.class);

    public static void main(String[] args) {
        LOGGER.info("STARTING THE APPLICATION");
        SpringApplication.run(KinopoiskConsoleApplication.class, args);
        LOGGER.info("APPLICATION FINISHED");
    }


}
