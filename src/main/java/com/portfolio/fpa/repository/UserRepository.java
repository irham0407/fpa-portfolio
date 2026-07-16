package com.portfolio.fpa.repository;

import com.portfolio.fpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Mencari user berdasarkan username (sangat berguna saat proses login)
    Optional<User> findByUsername(String username);

    // Mencari user berdasarkan email (opsi login alternatif atau verifikasi akun)
    Optional<User> findByEmail(String email);

    // Mengecek apakah username sudah digunakan (untuk validasi saat registrasi)
    boolean existsByUsername(String username);

    // Mengecek apakah email sudah digunakan (untuk validasi saat registrasi)
    boolean existsByEmail(String email);

    // Mengecek apakah sudah ada akun dengan role ADMIN di database
    // (Karena kebutuhan Anda: akun Admin di sistem ini hanya boleh ada 1)
    boolean existsByRole(String role);
}
