package com.portfolio.fpa.dto.authdto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Long branchId;
}
