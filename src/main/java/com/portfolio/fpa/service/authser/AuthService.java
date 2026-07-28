package com.portfolio.fpa.service.authser;

import com.portfolio.fpa.dto.authdto.AuthResponse;
import com.portfolio.fpa.dto.authdto.LoginRequest;
import com.portfolio.fpa.dto.authdto.RegisterRequest;
import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.User;
import com.portfolio.fpa.repository.BranchRepository;
import com.portfolio.fpa.repository.UserRepository;
import com.portfolio.fpa.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Gagal: Username sudah digunakan!");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Gagal: Email sudah terdaftar!");
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Gagal: Nomor HP sudah terdaftar!");
        }

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RuntimeException("Gagal: Cabang tidak ditemukan!"));

        User newUser = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole()) // Pastikan Frontend/Postman mengirimkan "ADMIN" atau "USER"
                .branch(branch)
                // Field baru
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .jobTitle(request.getJobTitle())
                .build();

        userRepository.save(newUser);

        // Mengembalikan AuthResponse DTO
        return AuthResponse.builder()
                .message("Registrasi user berhasil untuk cabang: " + branch.getBranchName())
                .username(newUser.getUsername())
                .fullName(newUser.getFullName())
                .role(newUser.getRole())
                .branchName(branch.getBranchName())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        // Autentikasi otomatis menggunakan AuthenticationManager bawaan Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Generate Token JWT
        String token = jwtUtils.generateToken(request.getUsername());

        // Ambil data user untuk respons profil
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        // Mengembalikan AuthResponse DTO dengan Token
        return AuthResponse.builder()
                .message("Login berhasil! Selamat datang, " + user.getUsername())
                .token(token)
                .username(user.getUsername())
                .fullName(user.getFullName())
                .role(user.getRole())
                .branchName(user.getBranch().getBranchName())
                .build();
    }
}
