package ru.bjcreslin.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "voters", nullable = false)
    private Integer voters;

    @Column(name = "rating_value", nullable = false)
    private Double ratingValue;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Override
    public String toString() {
        return "Rating{" +
                "position=" + position +
                ", voters=" + voters +
                ", rating=" + ratingValue +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        if (!super.equals(o)) return false;

        Rating rating = (Rating) o;

        if (!Objects.equals(movie, rating.movie)) return false;
        return Objects.equals(date, rating.date);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}