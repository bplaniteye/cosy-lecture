package com.projet.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    //@Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    private Boolean available;

    @Column(nullable = false)
    private String  createdAt;

    @Column(nullable = false, length = 50)
    private Date publicationYear;

    @Column(length = 50)
    private String version;

    // Relations
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    public Collection<BorrowEntity> borrowCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private Collection<PictureEntity> pictureCollection;

    @ManyToOne
    private AuthorEntity author;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private EditorEntity editor;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        LocalDateTime localDateTime = now.toInstant().atZone(zoneId).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = localDateTime.format(formatter);
        createdAt = formattedDate;
    }
}
