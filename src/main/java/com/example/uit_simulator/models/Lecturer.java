package com.example.uit_simulator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lecturers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecturer extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lecturerId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToMany(mappedBy = "lecturers", fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
