package ru.bjcreslin.kinopoisk_console.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.exceptions.FileParsingFailed;
import ru.bjcreslin.kinopoisk_console.service.FileService;
import ru.bjcreslin.kinopoisk_console.service.RatingProvider;

import java.io.IOException;

import static ru.bjcreslin.kinopoisk_console.exceptions.FileParsingFailed.ERROR_PARSING_FROM_FILE;

@Service("fileProvider")
public class FileProvider implements RatingProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileProvider.class);

    public static final String DOWNLOADING_FROM_FILE = "Downloading from file {}";

    private final FileService fileService;

    private static final String ENCODING = "UTF-8";

    private static final String FILE_NAME = "250.html";

    public FileProvider(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Element getHtmlBody() {
        LOGGER.info(DOWNLOADING_FROM_FILE, FILE_NAME);
        Document doc;
        try {
            doc = Jsoup.parse(fileService.getFileFromResources(FILE_NAME), ENCODING);
            return doc.body();
        } catch (IOException | NullPointerException e) {
            LOGGER.error(ERROR_PARSING_FROM_FILE);
            throw new FileParsingFailed(ERROR_PARSING_FROM_FILE, e);
        }
    }
}
