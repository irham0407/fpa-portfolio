package com.portfolio.fpa.dto.revenucedto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class RevenueActualResponse {
    private Long id;
    private String actualCode;
    private Long branchId;
    private String branchCode;
    private String branchName;
    private Long coaId;
    private String coaCode;
    private String coaName;
    private BigDecimal actualAmount;
    private String description;
    private Integer periodMonth;
    private Integer periodYear;
    private Instant transactionDate;
    private Instant createdAt;
}
