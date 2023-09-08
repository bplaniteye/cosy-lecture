package com.projet.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.library.entities.NationalityEntity;

public interface NationalityRepository extends JpaRepository<NationalityEntity,Integer>{
    
}
