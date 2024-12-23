package com.example.uit_simulator.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterUserRequest {
    private String username;
    private String password;
    private String email;
    private Long roleId;
}

