package com.portfolio.fpa.config;

import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.Coa;
import com.portfolio.fpa.model.User;
import com.portfolio.fpa.repository.BranchRepository;
import com.portfolio.fpa.repository.CoaRepository;
import com.portfolio.fpa.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final CoaRepository coaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           BranchRepository branchRepository,
                           CoaRepository coaRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.coaRepository = coaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Inisialisasi Cabang Utama/Pusat jika belum ada
        Branch headOffice = branchRepository.findByBranchCode("HO-JKT")
                .orElseGet(() -> branchRepository.save(Branch.builder()
                        .branchCode("HO-JKT")
                        .branchName("Kantor Pusat Jakarta")
                        .location("Jakarta Pusat")
                        .build()));

        // 2. Inisialisasi Akun Admin Utama (Hanya 1 Admin di Sistem)
        if (!userRepository.existsByRole("ADMIN")) {
            User admin = User.builder()
                    .username("admin_fpa")
                    .email("admin@fpa-portfolio.com")
                    .password(passwordEncoder.encode("AdminPass123!")) // Password otomatis dienkripsi BCrypt
                    .role("ADMIN")
                    .branch(headOffice)
                    .build();
            userRepository.save(admin);
            System.out.println(">>> Akun Admin Utama Berhasil Dibuat: admin_fpa (Password: AdminPass123!)");
        }

        // 3. Inisialisasi Contoh Data COA OPEX (Kepala Kode 6)
        if (!coaRepository.existsByCoaCode("61010001")) {
            coaRepository.save(Coa.builder()
                    .coaCode("61010001")
                    .coaName("Beban Gaji & Tunjangan")
                    .accountType("OPEX")
                    .build());
        }

        if (!coaRepository.existsByCoaCode("61020001")) {
            coaRepository.save(Coa.builder()
                    .coaCode("61020001")
                    .coaName("Beban Sewa Gedung & Kantor")
                    .accountType("OPEX")
                    .build());
        }
    }
    }
}
