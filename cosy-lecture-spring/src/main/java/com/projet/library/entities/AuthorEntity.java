package com.projet.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String firstname;

    @Column(length = 50, nullable = false)
    private String lastname;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate deathday;

    @Column(columnDefinition = "TEXT")
    private String bio;

    //Relations
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Collection<BookEntity> bookCollection;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "author_country",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Collection<NationalityEntity> nationalityCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Collection<PictureEntity> pictureCollection;
}
