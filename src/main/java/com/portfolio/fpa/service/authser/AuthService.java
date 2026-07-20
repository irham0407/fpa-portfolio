package com.portfolio.fpa.service.authser;

import com.portfolio.fpa.dto.authdto.LoginRequest;
import com.portfolio.fpa.dto.authdto.RegisterRequest;
import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.User;
import com.portfolio.fpa.repository.BranchRepository;
import com.portfolio.fpa.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       BranchRepository branchRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegisterRequest request) {
        // Validasi keunikan username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username sudah digunakan!");
        }

        // Validasi keunikan email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email sudah terdaftar!");
        }

        // Cari data cabang yang dituju
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RuntimeException("Cabang tidak ditemukan!"));

        // Buat user baru (Role dikunci sebagai "USER")
        User newUser = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Enkripsi BCrypt
                .role("USER")
                .branch(branch)
                .build();

        userRepository.save(newUser);
        return "Registrasi user berhasil untuk cabang: " + branch.getBranchName();
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username atau password salah!"));

        // Verifikasi password yang di-input dengan hash di database
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Username atau password salah!");
        }

        return "Login berhasil! Selamat datang, " + user.getUsername() + " (" + user.getRole() + ")";
    }
}
