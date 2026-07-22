package com.portfolio.fpa.dto.capexdto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class CapexBudgetResponse {
    private Long id;
    private String budgetCode;
    private Long branchId;
    private String branchCode;
    private String branchName;
    private Long coaId;
    private String coaCode;
    private String coaName;
    private BigDecimal budgetAmount;
    private Integer periodMonth;
    private Integer periodYear;
    private Instant createdAt;
}
