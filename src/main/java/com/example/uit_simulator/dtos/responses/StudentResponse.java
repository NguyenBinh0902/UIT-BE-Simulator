package com.example.uit_simulator.dtos.responses;

import com.example.uit_simulator.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String sid;
    private Integer course;
    private ProfileResponse profile;

    public StudentResponse toDTO(Student student) {
        this.setId(student.getId());
        this.setSid(student.getSid());
        this.setCourse(student.getCourse());
        this.setProfile(new ProfileResponse().toDTO(student.getProfile()));
        return this;
    }
}
