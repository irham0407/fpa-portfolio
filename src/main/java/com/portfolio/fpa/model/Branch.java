package com.portfolio.fpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "branches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tambahan kolom Branch Code (Misal: "JKT" untuk Jakarta, "SUB" untuk Surabaya)
    @Column(name = "branch_code", nullable = false, unique = true, length = 10)
    private String branchCode;

    @Column(nullable = false, unique = true)
    private String branchName;

    @Column(nullable = false)
    private String location;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        // Otomatis mencatat waktu pembuatan berdasarkan waktu sistem pusat (UTC+7)
        this.createdAt = Instant.now();
    }
}
