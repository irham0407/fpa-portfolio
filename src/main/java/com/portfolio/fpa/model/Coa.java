package com.portfolio.fpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "coas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // COA Code: Tipe data String, panjang tepat 8 karakter, berisi angka, dan unik
    @Column(name = "coa_code", nullable = false, unique = true, length = 8)
    private String coaCode;

    // Nama Akun (Contoh: "Beban Gaji Karyawan", "Beban Sewa Gedung")
    @Column(name = "coa_name", nullable = false, length = 150)
    private String coaName;

    // Untuk mengelompokkan tipe akun (Contoh: "OPEX", "CAPEX", "REVENUE", dll.)
    @Column(nullable = false, length = 30)
    private String accountType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }
}
