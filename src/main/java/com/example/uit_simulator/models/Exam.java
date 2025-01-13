package com.example.uit_simulator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "shift")
    private String shift;
    @Column(name = "room")
    private String room;
    @Column(name = "exam_type")
    private String examType;
    @Column(name = "semester")
    private int semester;
    @Column(name = "year")
    private int year;
}
