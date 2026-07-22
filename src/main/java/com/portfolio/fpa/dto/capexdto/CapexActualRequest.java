package com.portfolio.fpa.dto.capexdto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CapexActualRequest {
    private Long branchId;
    private Long coaId;
    private BigDecimal actualAmount;
    private String description;
    private Integer periodMonth;
    private Integer periodYear;
    private Instant transactionDate;
}
