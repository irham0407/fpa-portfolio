package com.portfolio.fpa.model.capexmodel;

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
@Table(name = "capex_budget")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapexBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "budget_code", nullable = false, unique = true, length = 60)
    private String budgetCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coa_id", nullable = false)
    private Coa coa;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal budgetAmount;

    @Column(nullable = false)
    private Integer periodMonth;

    @Column(nullable = false)
    private Integer periodYear;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    @PreUpdate
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Instant.now();
        }
        this.budgetCode = String.format("%s-%s%d%d",
                this.branch.getBranchCode(),
                this.coa.getCoaCode(),
                this.periodMonth,
                this.periodYear
        );
        if (this.budgetCode.length() > 60) {
            this.budgetCode = this.budgetCode.substring(0, 60);
        }
    }
}
