package ru.bjcreslin.kinopoisk_web.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie extends AbstractPersistable<Long> implements Serializable {

    @Column(name = "name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Column(name = "original_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @NaturalId
    private String originalName;

    @Column(name = "year", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @NaturalId
    private String year;

    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movieRatingPK.movie")
    private Set<Rating> rating = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Movie movie = (Movie) o;

        return originalName.equals(movie.originalName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + originalName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", year='" + year + '\'' +
                ", rating=" + rating +
                '}';
    }
}