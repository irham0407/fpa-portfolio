package com.portfolio.fpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean untuk Enkripsi Password menggunakan BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean Konfigurasi Keamanan HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Nonaktifkan CSRF untuk kemudahan pengujian REST API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Endpoint Login/Register dibuka publik
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Endpoint khusus Admin
                        .anyRequest().authenticated() // Sisa endpoint wajib login/autentikasi
                )
                .httpBasic(httpBasic -> {}); // Menggunakan HTTP Basic Auth untuk pengujian via Insomnia/Postman

        return http.build();
    }
}
