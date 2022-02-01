package ru.bjcreslin.kinopoisk_console.service.impl;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.bjcreslin.kinopoisk_console.exceptions.FileParsingFailed;
import ru.bjcreslin.kinopoisk_console.service.FileService;
import ru.bjcreslin.kinopoisk_console.service.RatingProvider;

import java.io.File;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class FileProviderTest {

    private final RatingProvider provider;

    private final FileService fileService;

    FileProviderTest() {
        fileService = Mockito.mock(FileService.class);
        provider = new FileProvider(fileService);
    }

    @Test
    void shouldElementClass() {
        when(fileService.getFileFromResources(anyString())).thenReturn(getFile());
        assertEquals(Element.class.getName(), provider.getHtmlBody().getClass().getName());
    }

    @Test
    void shouldException() {
        when(fileService.getFileFromResources(anyString())).thenThrow(new NullPointerException());
        Exception exception = assertThrows(FileParsingFailed.class, provider::getHtmlBody);
        assertTrue(exception.getMessage().contains(FileParsingFailed.ERROR_PARSING_FROM_FILE));
    }

    private File getFile() {
        return new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("test-html.html")).getFile());
    }
}