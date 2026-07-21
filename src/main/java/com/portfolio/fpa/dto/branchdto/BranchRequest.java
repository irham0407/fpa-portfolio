package com.portfolio.fpa.dto.branchdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequest {
    private String branchCode;
    private String branchName;
    private String location;
}
