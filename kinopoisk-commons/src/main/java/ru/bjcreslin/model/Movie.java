package ru.bjcreslin.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie extends AbstractPersistable<Long> {

    @Column(name = "name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Column(name = "original_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String originalName;

    @Column(name = "year", nullable = false)
    private LocalDate year;

    @OneToMany(mappedBy = "movie")
    private Set<Rating> rating = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        if (!super.equals(o)) return false;

        Movie movie = (Movie) o;

        if (!originalName.equals(movie.originalName)) return false;
        return year.equals(movie.year);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + originalName.hashCode();
        result = 31 * result + year.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", year=" + year +
                '}';
    }
}