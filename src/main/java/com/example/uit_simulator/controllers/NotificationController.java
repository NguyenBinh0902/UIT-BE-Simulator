package com.example.uit_simulator.controllers;

import com.example.uit_simulator.services.NotificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('SV')")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/getNotifications")
    public ResponseEntity<?> getNotifications(@RequestParam int page, @RequestParam int size) {
        try {
            return ResponseEntity.ok(notificationService.getPersonalNotifies(page, size));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
