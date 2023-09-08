package com.projet.library.repositories;

import com.projet.library.entities.LibraryUserEntity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface LibraryUserRepository extends JpaRepository<LibraryUserEntity, Integer> {

	Optional<LibraryUserEntity> findByEmail(String email);
	
	Optional<LibraryUserEntity> findById(Integer id);

	//Optional<LibraryUserEntity> loadByUsername(String email);
	
	//Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
