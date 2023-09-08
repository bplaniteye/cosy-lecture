package com.projet.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "editor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "editor")
    private Collection<BookEntity> bookCollection;
}
