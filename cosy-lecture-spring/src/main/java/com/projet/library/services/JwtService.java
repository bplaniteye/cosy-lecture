package com.projet.library.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    public String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
    
    int extractUserId(String userId);

}

