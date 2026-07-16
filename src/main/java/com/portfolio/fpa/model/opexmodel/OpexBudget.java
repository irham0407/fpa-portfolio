package com.portfolio.fpa.model.opexmodel;

import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.Coa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "opex_budgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpexBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kode Anggaran Unik (Format: Branchkode-kodeKategoriMYYYY, maks 60 karakter)
    @Column(name = "budget_code", nullable = false, unique = true, length = 60)
    private String budgetCode;

    // Relasi ke cabang: Anggaran ini dialokasikan untuk cabang mana
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    // Menggantikan opex_code_category & opex_category dengan relasi COA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coa_id", nullable = false)
    private Coa coa;

    // Menggunakan BigDecimal demi akurasi perhitungan finansial mata uang
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal budgetAmount;

    @Column(nullable = false)
    private Integer periodMonth; // Nilai 1-12 untuk representasi Bulan

    @Column(nullable = false)
    private Integer periodYear;  // Contoh: 2026

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    @PreUpdate
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Instant.now();
        }
        // Otomatis men-generate format: JKT-OPX-0112026 (BranchCode-OpexCode-MonthYear)
        this.budgetCode = String.format("%s-%s%d%d",
                this.branch.getBranchCode(),
                this.coa.getCoaCode(),
                this.periodMonth,
                this.periodYear
        );

        // Validasi agar panjang kode tidak melebihi batas database (60 karakter)
        if (this.budgetCode.length() > 60) {
            this.budgetCode = this.budgetCode.substring(0, 60);
        }
    }
}
