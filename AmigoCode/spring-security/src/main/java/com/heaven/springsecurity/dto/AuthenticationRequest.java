package com.heaven.springsecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
