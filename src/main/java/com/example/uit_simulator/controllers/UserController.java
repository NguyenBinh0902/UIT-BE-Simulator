package com.example.uit_simulator.controllers;

import com.example.uit_simulator.dtos.responses.StudentResponse;
import com.example.uit_simulator.dtos.responses.UserResponse;
import com.example.uit_simulator.models.Profile;
import com.example.uit_simulator.models.Student;
import com.example.uit_simulator.models.User;
import com.example.uit_simulator.repositories.ProfileRepository;
import com.example.uit_simulator.repositories.StudentRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@PreAuthorize("hasRole('SV')")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    ProfileRepository profileRepository;
    StudentRepository studentRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        if (user.getStudent() != null) {
            Student student = studentRepository.findById(user.getStudent().getId()).orElseThrow(() -> new RuntimeException("Student not found"));
            userResponse.setStudent(new StudentResponse().toDTO(student));
        }

        return ResponseEntity.ok(userResponse);
    }
}
