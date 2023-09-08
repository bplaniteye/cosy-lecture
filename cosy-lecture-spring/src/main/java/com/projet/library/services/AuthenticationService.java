package com.projet.library.services;

import com.projet.library.dao.request.AuthenticationRequest;
import com.projet.library.dao.request.RegisterRequest;
import com.projet.library.dao.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}

