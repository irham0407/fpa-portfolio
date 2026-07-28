package com.portfolio.fpa.dto.authdto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Long branchId;
    private String role;
    private String fullName;
    private String phoneNumber;
    private String jobTitle;
}
