package com.example.uit_simulator.dtos.responses;


import com.example.uit_simulator.models.Lecturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LecturerResponse {
    private Long id;
    private String lecturerId;
    private String name;
    private String email;


    public LecturerResponse toDTO(Lecturer lecturer) {
        this.setId(lecturer.getId());
        this.setLecturerId(lecturer.getLecturerId());
        this.setName(lecturer.getProfile().getName());
        this.setEmail(lecturer.getProfile().getEmail());
        return this;
    }

    public List<LecturerResponse> mapLecturersToDTO(List<Lecturer> lecturers) {
        return lecturers.stream().map(lecturer -> new LecturerResponse().toDTO(lecturer)).toList();
    }
}
