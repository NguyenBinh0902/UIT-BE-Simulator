package com.example.uit_simulator.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String thu; // Thứ (3, 4,...)
    private String tiet; // Tiết (1-3, 4-5,...)
    private String phonghoc; // Phòng học
    private boolean online; // Online hay offline
    private LocalDate ngaybd; // Ngày bắt đầu
    private LocalDate ngaykt; // Ngày kết thúc

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;
}
