package com.portfolio.fpa.dto.reportdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialReportResponse {

    private String reportId;
    private Integer periodMonth;
    private Integer periodYear;
    private Instant generatedAt;

    // --- REVENUE AGGREGATION ---
    private BigDecimal totalRevenueBudget;
    private BigDecimal totalRevenueActual;
    private BigDecimal revenueVariance;
    private Double revenueVariancePercentage;

    // --- OPEX AGGREGATION ---
    private BigDecimal totalOpexBudget;
    private BigDecimal totalOpexActual;
    private BigDecimal opexVariance;
    private Double opexVariancePercentage;

    // --- CAPEX AGGREGATION ---
    private BigDecimal totalCapexBudget;
    private BigDecimal totalCapexActual;
    private BigDecimal capexVariance;
    private Double capexVariancePercentage;

    // --- NET PROFIT / SUMMARY INDIKATOR ---
    private BigDecimal netOperatingIncomeBudget; // Total Revenue Budget - Total Opex Budget
    private BigDecimal netOperatingIncomeActual; // Total Revenue Actual - Total Opex Actual
    private BigDecimal netOperatingIncomeVariance;

    // --- LINE-ITEM BREAKDOWN ---
    private List<FinancialVarianceDTO> details; // Total Revenue Actual - (Opex Actual + Capex Actual)
}
