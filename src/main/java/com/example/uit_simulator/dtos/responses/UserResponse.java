package com.example.uit_simulator.dtos.responses;

import com.example.uit_simulator.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id; // ID của user
    private String username;
    private String email;
    private StudentResponse student; // Thông tin student

    // Constructor
    public UserResponse toDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.student = new StudentResponse().toDTO(user.getStudent());
        return this;
    }
}
