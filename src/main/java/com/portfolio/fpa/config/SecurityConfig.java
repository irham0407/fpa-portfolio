package com.portfolio.fpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

    // Bean AuthenticationManager (Diperlukan agar proses Login di AuthController berjalan)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Bean Konfigurasi Keamanan HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Nonaktifkan CSRF untuk kemudahan pengujian REST API
                .authorizeHttpRequests(auth -> auth
                        // 1. Endpoint Autentikasi (Publik)
                        .requestMatchers("/api/auth/**").permitAll()

                        // 2. Master Data (Diizinkan publik untuk kemudahan seeding/testing)
                        .requestMatchers("/api/branches/**", "/api/coas/**").permitAll()

                        // 3. BARU: SEMUA ENDPOINT ACTUAL (OPEX, REVENUE, CAPEX, dll.) -> HANYA ADMIN
                        .requestMatchers("/api/*-actuals/**").hasRole("ADMIN")

                        // 4. BARU: SEMUA ENDPOINT BUDGET (OPEX, REVENUE, CAPEX, dll.) -> ADMIN & USER (Asal Sudah Login)
                        .requestMatchers("/api/*-budgets/**").authenticated()

                        // 5. Endpoint Khusus Admin Lainnya
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // 6. Sisa Endpoint Lain Wajib Authenticated
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}); // Menggunakan HTTP Basic Auth untuk pengujian via Insomnia/Postman

        return http.build();
    }
}
