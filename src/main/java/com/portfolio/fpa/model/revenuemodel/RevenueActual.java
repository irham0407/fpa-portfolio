package com.portfolio.fpa.model.revenuemodel;

import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.Coa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "revenue_actual")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueActual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Column(name = "actual_code", nullable = false, unique = true, length = 60)
    private String actualCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coa_id", nullable = false)
    private Coa coa;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal actualAmount;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Integer periodMonth;

    @Column(nullable = false)
    private Integer periodYear;

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
        // Format otomatis: KodeBranch-KodeCOABulanTahun
        this.actualCode = String.format("%s-%s%d%d",
                this.branch.getBranchCode(),
                this.coa.getCoaCode(),
                this.periodMonth,
                this.periodYear
        );
        if (this.actualCode.length() > 60) {
            this.actualCode = this.actualCode.substring(0, 60);
        }
    }}
