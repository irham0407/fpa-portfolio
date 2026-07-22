package com.portfolio.fpa.dto.reportdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialSummaryResponse {
    private Integer periodMonth;
    private Integer periodYear;

    // Revenue Summary
    private BigDecimal totalRevenueBudget;
    private BigDecimal totalRevenueActual;
    private BigDecimal totalRevenueVariance;

    // Opex Summary
    private BigDecimal totalOpexBudget;
    private BigDecimal totalOpexActual;
    private BigDecimal totalOpexVariance;

    // Capex Summary
    private BigDecimal totalCapexBudget;
    private BigDecimal totalCapexActual;
    private BigDecimal totalCapexVariance;

    // Line-item detail
    private List<FinancialVarianceDTO> details;
}
