package ru.bjcreslin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class MovieWithRatingDto {
    private final String name;
    private final String originalName;
    private final String year;
    private final Integer position;
    private final Integer voters;
    private final Double ratingValue;
    private final LocalDate date;

    @Override
    public String toString() {
        return "MovieWithRatingDto{" +
                "name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", year='" + year + '\'' +
                ", position=" + position +
                ", voters=" + voters +
                ", ratingValue=" + ratingValue +
                ", date=" + date +
                '}';
    }
}
