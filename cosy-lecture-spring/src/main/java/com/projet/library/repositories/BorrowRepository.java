package com.projet.library.repositories;

// import com.projet.library.entities.BookEntity;
import com.projet.library.entities.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowRepository extends JpaRepository<BorrowEntity, Integer> {
    @Query("SELECT b FROM BorrowEntity b WHERE b.user.id = :userId")
    List<BorrowEntity> findByUserId(@Param("userId") Integer userId);
}
