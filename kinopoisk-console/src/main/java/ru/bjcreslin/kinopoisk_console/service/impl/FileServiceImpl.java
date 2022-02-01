package ru.bjcreslin.kinopoisk_console.service.impl;

import org.springframework.stereotype.Service;
import ru.bjcreslin.kinopoisk_console.service.FileService;

import java.io.File;
import java.net.URL;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("html/"+fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
