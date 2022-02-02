package ru.bjcreslin.kinopoisk_console.model;

import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class MovieRatingPK implements Serializable {

    private static final long serialVersionUID = -1571748779013286215L;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
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
}