package com.projet.library.repositories;

import com.projet.library.entities.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<PictureEntity, Integer> {
}
