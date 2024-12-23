package com.example.uit_simulator.dtos.responses;

import com.example.uit_simulator.models.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String name;
    private String email;
    private Integer status;
    private String major;
    private LocalDate dob;
    private String role;
    private String className;
    private String address;
    private String avatarUrl;

    public ProfileResponse toDTO(Profile profile) {
        this.name = profile.getName();
        this.email = profile.getEmail();
        this.status = profile.getStatus();
        this.major = profile.getMajor();
        this.dob = profile.getDob();
        this.role = profile.getRole();
        this.className = profile.getClassName();
        this.address = profile.getAddress();
        this.avatarUrl = profile.getAvatarUrl();
        return this;
    }
}
