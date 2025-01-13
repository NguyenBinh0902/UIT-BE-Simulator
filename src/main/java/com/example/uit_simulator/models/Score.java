package com.example.uit_simulator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "scores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "diem")
    private String diem;
    @Column(name = "diem1")
    private String diem1;
    @Column(name = "diem2")
    private String diem2;
    @Column(name = "diem3")
    private String diem3;
    @Column(name = "diem4")
    private String diem4;
    @Column(name = "heso1")
    private String heso1;
    @Column(name = "heso2")
    private String heso2;
    @Column(name = "heso3")
    private String heso3;
    @Column(name = "heso4")
    private String heso4;
}
