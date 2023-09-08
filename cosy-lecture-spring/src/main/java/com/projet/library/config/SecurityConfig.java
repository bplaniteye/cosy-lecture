package com.projet.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .cors(Customizer.withDefaults())
                        .authorizeHttpRequests(request ->
                                request
                                .requestMatchers(
                                        "/api/auth/**",
                                        "/api/book/**",
                                        "/api/category/**",
                                        "/api/author/**",
                                        "/api/borrow/**",
                                        "/**",
                                        "/api/admin/**"
                                ).permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/borrow/createBorrow").hasRole("USER")
                                .requestMatchers(HttpMethod.GET,"/api/libraryUser/profile/**").hasAnyRole("USER")
                                .requestMatchers(HttpMethod.POST,"/api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider).addFilterBefore(
                                jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }
}



//http
                                //.logout(request ->
                                //request
                                //.logoutUrl("/logout") // L'URL de déconnexion
                                //.invalidateHttpSession(true) // Invalider la session
                                //.deleteCookies("JSESSIONID") // Supprimer les cookies si nécessaire
                                //.clearAuthentication(true) // Effacer l'authentification
                                //.logoutSuccessUrl("/home")); // Rediriger après déconnexion