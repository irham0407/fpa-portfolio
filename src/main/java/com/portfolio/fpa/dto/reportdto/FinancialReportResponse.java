package com.portfolio.fpa.dto.reportdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialReportResponse {

    // Summary Revenue
    private BigDecimal totalRevenueBudget;
    private BigDecimal totalRevenueActual;
    private BigDecimal revenueVariance; // Actual - Budget

    // Summary OPEX
    private BigDecimal totalOpexBudget;
    private BigDecimal totalOpexActual;
    private BigDecimal opexVariance;

    // Summary CAPEX
    private BigDecimal totalCapexBudget;
    private BigDecimal totalCapexActual;
    private BigDecimal capexVariance;

    // Financial Health Status
    private BigDecimal netProfitBudget; // Total Revenue Budget - (Opex Budget + Capex Budget)
    private BigDecimal netProfitActual; // Total Revenue Actual - (Opex Actual + Capex Actual)
}
