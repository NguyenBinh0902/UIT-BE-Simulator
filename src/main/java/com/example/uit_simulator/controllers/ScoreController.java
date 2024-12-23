package com.example.uit_simulator.controllers;

import com.example.uit_simulator.dtos.responses.ScoreResponse;
import com.example.uit_simulator.models.Score;
import com.example.uit_simulator.repositories.ScoreRepository;
import com.example.uit_simulator.services.ScoreService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@AllArgsConstructor
@PreAuthorize("hasRole('SV')")
@SecurityRequirement(name = "bearerAuth")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/getScores")
    public ResponseEntity<?> getScores() {
        try {
            return scoreService.getStudentScores();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
