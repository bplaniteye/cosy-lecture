package com.projet.library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.library.dao.request.AuthenticationRequest;
import com.projet.library.dao.request.RefreshTokenRequest;
import com.projet.library.dao.request.RegisterRequest;
import com.projet.library.dao.response.AuthenticationResponse;
import com.projet.library.dao.response.RefreshTokenResponse;
import com.projet.library.services.AuthenticationService;
import com.projet.library.services.RefreshTokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(refreshTokenService.generateNewToken(request));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.noContent().build(); // Retourne un code 204 No Content
    }
}
