package ru.bjcreslin.kinopoisk_web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DateDto implements Serializable {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @Override
    public String toString() {
        return "DateDto{" +
                "date=" + date +
                '}';
    }
}
