package com.projet.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.library.entities.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
    
}
