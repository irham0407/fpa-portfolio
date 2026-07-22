package com.portfolio.fpa.dto.revenucedto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RevenueBudgetRequest {
    private Long branchId;
    private Long coaId;
    private BigDecimal budgetAmount;
    private Integer periodMonth;
    private Integer periodYear;
}
}
