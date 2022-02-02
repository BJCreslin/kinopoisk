package ru.bjcreslin.kinopoisk_console.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "rating")
@EntityListeners(AuditingEntityListener.class)
public class Rating {

    @EmbeddedId
    private MovieRatingPK movieRatingPK;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "voters", nullable = false)
    private Integer voters;

    @Column(name = "rating_value", nullable = false)
    private Double ratingValue;

    @ManyToOne
    @JoinColumn(name = "movie_rating_pk_movie_id")
    private Movie movieRatingPK_movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;

        Rating rating = (Rating) o;

        return Objects.equals(movieRatingPK, rating.movieRatingPK);
    }

    @Override
    public int hashCode() {
        return movieRatingPK != null ? movieRatingPK.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "movieRatingPK=" + movieRatingPK +
                ", position=" + position +
                ", voters=" + voters +
                ", ratingValue=" + ratingValue +
                '}';
    }
}