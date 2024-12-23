package com.example.uit_simulator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Tiêu đề thông báo
    @Column(length = 5000)
    private String content; // Nội dung thông báo
    private String type; // Loại thông báo (VD: HP - Học phí, BB - Học bù, ...)
    private String member; // Đối tượng nhận thông báo (VD: SV - Sinh viên)
    private LocalDateTime dated; // Thời gian thông báo được tạo
    private int hocky; // Học kỳ
    private int namhoc; // Năm học

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
