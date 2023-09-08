package com.projet.library.dao.request;

import java.time.LocalDateTime;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.projet.library.entities.BookEntity;
import com.projet.library.entities.LibraryUserEntity;

//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBorrowRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isReturned;
    private LibraryUserEntity user;
    private BookEntity book;
}
