package com.portfolio.fpa.dto.authdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String message;     // Pesan sukses/gagal
    private String token;       // JWT Token (berisi saat login)
    private String username;
    private String fullName;
    private String role;
    private String branchName;
}
