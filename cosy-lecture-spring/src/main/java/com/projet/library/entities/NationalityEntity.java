package com.projet.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class NationalityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    //Relationships
    @ManyToMany
    @JoinTable(
            name = "author_country",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Collection <AuthorEntity> authorCollection;

}
