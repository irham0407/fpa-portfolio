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
@Table(name = "opex_actuals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpexActual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    // Relasi ke cabang: Transaksi aktual ini terjadi di cabang mana
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    // Kode Aktual Unik (Format: Branchkode-kodeKategoriMYYYY, maks 60 karakter)
    @Column(name = "actual_code", nullable = false, unique = true, length = 60)
    private String actualCode;

    // Menggantikan opex_code_category & opex_category dengan relasi COA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coa_id", nullable = false)
    private Coa coa;

    // Nominal pengeluaran riil di lapangan
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal actualAmount;

    // Keterangan tambahan untuk transaksi aktual (misal: "Bayar tagihan listrik cabang Surabaya")
    @Column(nullable = false, length = 500)
    private String description;

    // Filter Periode Laporan Bulanan & Tahunan (Agar mudah ditarik & disandingkan dengan Budget)
    @Column(nullable = false)
    private Integer periodMonth; // Nilai 1-12 untuk representasi Bulan

    @Column(nullable = false)
    private Integer periodYear;  // Contoh: 2026

    // Tanggal transaksi riil terjadi di lapangan (bisa bervariasi sesuai zona waktu cabang input)
    @Column(name = "transaction_date", nullable = false)
    private Instant transactionDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    @PreUpdate
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Instant.now();
        }
        // Otomatis men-generate format: JKT-6101000112026
        this.actualCode = String.format("%s-%s%d%d",
                this.branch.getBranchCode(),
                this.coa.getCoaCode(),
                this.periodMonth,
                this.periodYear
        );

        if (this.actualCode.length() > 60) {
            this.actualCode = this.actualCode.substring(0, 60);
        }
    }
}
