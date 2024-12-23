package com.example.uit_simulator.controllers;

import com.example.uit_simulator.dtos.responses.ExamResponse;
import com.example.uit_simulator.services.ExamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('SV')")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/exams")
public class ExamController {
    private final ExamService examService;

    @GetMapping("/getExams")
    public ResponseEntity<?> getExamSchedules(
            @RequestParam String examType, // Loại kỳ thi: giữa kỳ hoặc cuối kỳ
            @RequestParam int semester,    // Học kỳ
            @RequestParam int year      // Năm học
    ) {
        try {
            return ResponseEntity.ok(examService.getExamResponses( examType, semester, year));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
