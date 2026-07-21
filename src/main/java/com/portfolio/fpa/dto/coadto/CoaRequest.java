package com.portfolio.fpa.dto.coadto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoaRequest {
    private String coaCode;
    private String coaName;
    private String accountType;
}
