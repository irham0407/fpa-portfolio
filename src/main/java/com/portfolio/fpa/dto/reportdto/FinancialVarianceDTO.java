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
public class FinancialVarianceDTO {
    private String category;           // OPEX, CAPEX, atau REVENUE
    private Long branchId;
    private String branchCode;
    private String branchName;
    private Long coaId;
    private String coaCode;
    private String coaName;
    private Integer periodMonth;
    private Integer periodYear;
    private BigDecimal budgetAmount;
    private BigDecimal actualAmount;
    private BigDecimal variance;       // Actual - Budget
    private Double variancePercentage;  // ((Actual - Budget) / Budget) * 100
}
