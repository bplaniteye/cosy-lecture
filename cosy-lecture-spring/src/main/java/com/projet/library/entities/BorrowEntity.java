package com.projet.library.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrow")
public class BorrowEntity {

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // TODO : turn into current date after testing
    // When the borrow is created, the start date is set to the current date
    @Column(nullable = false)
    private LocalDateTime startDate;

    // When the borrow is created, the end date is set to the current date + 30 days
    @Column(nullable = false)
    private LocalDateTime endDate;

    // TODO : turn into current date after testing
    // When the borrow is returned, the return date is set to the current date
    private LocalDateTime returnDate;

    // When the borrow is created, the return statement is set to false
    private Boolean isReturned;

    // When the borrow is returned, the delay is calculated between the end date and the return date
    private long borrowDelay;

    private Float penalty;

    //Relationships
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    //@NotNull
    private LibraryUserEntity user;

    @JsonIgnore
    @ManyToOne
    @NotNull
    private BookEntity book;

    @PrePersist
    protected void onCreate() {
        startDate = LocalDateTime.now();
        System.out.println(startDate);
        endDate = startDate.plusDays(30);
        System.out.println(endDate);
    }
}