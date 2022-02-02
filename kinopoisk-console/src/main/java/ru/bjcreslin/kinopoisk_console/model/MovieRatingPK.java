package ru.bjcreslin.kinopoisk_console.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class MovieRatingPK implements Serializable {

    private static final long serialVersionUID = -1571748779013286215L;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Movie movie;

    @Column(name = "date")
    @CreatedDate
    private LocalDate date;

    protected MovieRatingPK() {
    }

    public MovieRatingPK(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieRatingPK{" +
                "movie=" + movie.getName() +
                ", date=" + date +
                '}';
    }
}