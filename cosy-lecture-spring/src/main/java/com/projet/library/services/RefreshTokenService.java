package com.projet.library.services;

import java.util.Optional;

import com.projet.library.dao.request.RefreshTokenRequest;
import com.projet.library.dao.response.RefreshTokenResponse;
import com.projet.library.entities.RefreshToken;



public interface RefreshTokenService {

    RefreshToken createRefreshToken(Integer userId);
    RefreshToken verifyExpiration(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);

    RefreshTokenResponse generateNewToken(RefreshTokenRequest request);

}
