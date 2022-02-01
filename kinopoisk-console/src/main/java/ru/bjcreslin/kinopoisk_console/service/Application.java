package ru.bjcreslin.kinopoisk_console.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Application implements CommandLineRunner {

    private final Kinopoisk kinopoisk;

    public Application(Kinopoisk kinopoisk) {
        this.kinopoisk = kinopoisk;
    }

    @Override
    public void run(String... args) throws Exception {
          kinopoisk.get();
    }
}
