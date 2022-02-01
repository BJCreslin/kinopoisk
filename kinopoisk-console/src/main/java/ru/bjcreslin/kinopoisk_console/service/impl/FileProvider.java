package ru.bjcreslin.kinopoisk_console.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.service.FileService;
import ru.bjcreslin.kinopoisk_console.service.RatingProvider;

import java.io.IOException;

@Service("fileProvider")
public class FileProvider implements RatingProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebProviderImpl.class);

    @Autowired
    FileService fileService;

    private static final String ENCODING = "UTF-8";
    private static final String fileName = "250.html";

    @Override
    public Element get() {
        LOGGER.info("Downloaded from file");
        Document doc = null;
//        LOGGER.error(this.getClass().getClassLoader().getResource(fileName).toString());
        try {
            doc = Jsoup.parse(fileService.getFileFromResources(fileName), ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc.body();
    }
}
