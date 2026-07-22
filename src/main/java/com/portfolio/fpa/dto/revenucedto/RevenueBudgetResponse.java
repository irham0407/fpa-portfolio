package com.portfolio.fpa.dto.revenucedto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class RevenueBudgetResponse {
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
