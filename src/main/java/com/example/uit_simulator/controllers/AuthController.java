package com.example.uit_simulator.controllers;

import com.example.uit_simulator.dtos.requests.LoginUserRequest;
import com.example.uit_simulator.dtos.requests.RegisterUserRequest;
import com.example.uit_simulator.dtos.responses.AuthResponse;
import com.example.uit_simulator.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            AuthResponse response = authService.loginUser(loginUserRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            AuthResponse response = authService.registerUser(registerUserRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
