package com.example.uit_simulator.controllers;

import com.example.uit_simulator.enums.RoleName;
import com.example.uit_simulator.services.DataGenerationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fake-data")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class FakeDataController {
    private final DataGenerationService dataGenerationService;

    @PostMapping("/user/generate")
    public ResponseEntity<?> generateFakeData(@RequestParam RoleName role, @RequestParam Integer amount) {
        try {
            dataGenerationService.generateUserData(role, amount);
            return ResponseEntity.ok("Fake data generated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("schedule/generate")
    public ResponseEntity<?> generateFakeSchedule() {
        try {
            dataGenerationService.generateScheduleData();
            return ResponseEntity.ok("Fake schedule generated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("student-course/generate")
    public ResponseEntity<?> assignCoursesToStudents() {
        try {
            dataGenerationService.assignCoursesToStudents();
            return ResponseEntity.ok("Fake student-course generated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("score/generate")
    public ResponseEntity<?> generateFakeScore() {
        try {
            dataGenerationService.generateFakeScores();
            return ResponseEntity.ok("Fake score generated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("exam/generate")
    public ResponseEntity<?> generateFakeExam() {
        try {
            dataGenerationService.createFakeExams();
            return ResponseEntity.ok("Fake exam generated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("notification/generate")
    public ResponseEntity<?> generateFakeNotification() {
        try {
            dataGenerationService.createFakeNotifiesForStudents();
            return ResponseEntity.ok("Fake notification generated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
