package ru.bjcreslin.kinopoisk_console.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.service.FileService;

import java.io.File;
import java.net.URL;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final String HTML_DIR = "html/";

    public static final String FILE_IS_NOT_FOUND = "File %s is not found!";

    public static final String FILE_HAS_BEEN_LOADED = "File {} has been loaded";

    @Override
    public File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(HTML_DIR + fileName);
        if (resource == null) {
            var message = String.format(FILE_IS_NOT_FOUND, fileName);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(FILE_HAS_BEEN_LOADED, fileName);
            }
            return new File(resource.getFile());
        }
    }
}
