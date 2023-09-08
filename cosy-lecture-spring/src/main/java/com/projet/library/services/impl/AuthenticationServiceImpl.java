package com.projet.library.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.library.dao.request.AuthenticationRequest;
import com.projet.library.dao.request.RegisterRequest;
import com.projet.library.dao.response.AuthenticationResponse;
import com.projet.library.entities.LibraryUserEntity;
import com.projet.library.enums.TokenType;
import com.projet.library.exception.MailExistException;
import com.projet.library.repositories.LibraryUserRepository;
import com.projet.library.services.AuthenticationService;
import com.projet.library.services.JwtService;
import com.projet.library.services.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final LibraryUserRepository libraryUserRepository;
        private final AuthenticationManager authenticationManager;
        private final RefreshTokenService refreshTokenService;

        @Override
        public AuthenticationResponse register(RegisterRequest request) {
                var user = LibraryUserEntity.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(request.getRole())
                        .build();
                if (libraryUserRepository.existsByEmail(request.getEmail())) {
                        throw new MailExistException("L'e-mail existe déjà.");                                                                       //d'exception que vous souhaitez utiliser
                }
                user = libraryUserRepository.save(user);
                System.out.println(user);
                var jwt = jwtService.generateToken(user);
                var refreshToken = refreshTokenService.createRefreshToken(user.getId());
                return AuthenticationResponse.builder()
                        .accessToken(jwt)
                        .email(user.getEmail())
                        .id(user.getId())
                        .refreshToken(refreshToken.getToken())
                        .role(request.getRole())
                        .tokenType(TokenType.BEARER.name())
                        .build();
                }

        @Override
        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                if(authentication != null){
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        System.out.println("AuthenticationServiceImpl : " + authentication);
                }
                var user = libraryUserRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
                System.out.println(user);
                var jwt = jwtService.generateToken(user);
                System.out.println("JWT Token: " + jwt);
                var refreshToken = refreshTokenService.createRefreshToken(user.getId());
                return AuthenticationResponse.builder()
                        .accessToken(jwt)
                        .role(user.getRole())
                        .email(user.getEmail())
                        .id(user.getId())
                        .refreshToken(refreshToken.getToken())
                        .tokenType(TokenType.BEARER.name())
                        .build();
                }
}
                        // .firstName(request.getFirstName())
                        // .lastName(request.getLastName())