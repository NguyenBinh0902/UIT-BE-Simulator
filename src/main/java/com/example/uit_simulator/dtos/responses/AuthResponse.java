package com.example.uit_simulator.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Date expireTime;
    private String type = "Bearer";

    public AuthResponse(String token, Date expireTime) {
        this.token = token;
        this.expireTime = expireTime;
    }
}