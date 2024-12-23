package com.example.uit_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "special_schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate ngay; // Ngày diễn ra
    private String tiet; // Tiết học
    private String phonghoc; // Phòng học
    private boolean online; // Online hay offline
    private String lydo; // Lý do (Học bù, gặp sinh viên, ...)

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;
}
