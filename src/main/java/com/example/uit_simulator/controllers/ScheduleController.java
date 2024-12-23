package com.example.uit_simulator.controllers;

import com.example.uit_simulator.dtos.responses.ScheduleResponse;
import com.example.uit_simulator.services.ScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('SV')")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

//    @GetMapping("/week")
//    public ResponseEntity<?> getSchedulesForStudent() {
//         try{
//             return scheduleService.getSchedulesForStudent();
//         }catch (Exception e){
//                return ResponseEntity.badRequest().body(e.getMessage());
//         }
//    }

    @GetMapping("/week")
    public ResponseEntity<?> getWeeklySchedule( @RequestParam int hocky, @RequestParam int namhoc) {
        try {
            return scheduleService.getWeeklySchedule(hocky, namhoc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
