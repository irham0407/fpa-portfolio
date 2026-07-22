package com.portfolio.fpa.dto.capexdto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CapexBudgetRequest {
    private Long branchId;
    private Long coaId;
    private BigDecimal budgetAmount;
    private Integer periodMonth;
    private Integer periodYear;
}
