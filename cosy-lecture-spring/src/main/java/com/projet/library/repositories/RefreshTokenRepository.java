package com.projet.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.library.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

}
