package com.portfolio.fpa.config;

import com.portfolio.fpa.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. Endpoint Autentikasi (Publik)
                        .requestMatchers("/api/auth/**").permitAll()

                        // 2. Master Data (Diizinkan publik)
                        .requestMatchers("/api/branches/**", "/api/coas/**").permitAll()

                        // 3. SEMUA ENDPOINT ACTUAL (OPEX, REVENUE, CAPEX, dll.) -> HANYA ADMIN
                        .requestMatchers("/api/*-actuals/**").hasRole("ADMIN")

                        // 4. SEMUA ENDPOINT BUDGET (OPEX, REVENUE, CAPEX, dll.) -> ADMIN & USER (Asal Sudah Login)
                        .requestMatchers("/api/*-budgets/**").authenticated()

                        // 5. Endpoint Khusus Admin Lainnya
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // 6. Sisa Endpoint Lain Wajib Authenticated
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
