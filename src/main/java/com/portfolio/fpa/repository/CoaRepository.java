package com.portfolio.fpa.repository;

import com.portfolio.fpa.model.Coa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CoaRepository extends JpaRepository<Coa, Long> {

    // Mencari COA berdasarkan kode spesifiknya
    Optional<Coa> findByCoaCode(String coaCode);

    // Membantu pengecekan apakah kode COA sudah terdaftar saat inisialisasi database
    boolean existsByCoaCode(String coaCode);

    // Filter COA berdasarkan tipe akun (Contoh: "OPEX", "CAPEX")
    List<Coa> findByAccountType(String accountType);

    // Mencari COA yang diawali angka tertentu (Contoh: "6%" untuk mencari semua akun OPEX/Beban)
    List<Coa> findByCoaCodeStartingWith(String prefix);
}
