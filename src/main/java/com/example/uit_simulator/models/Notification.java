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
    @Column(name = "title")
    private String title; // Tiêu đề thông báo
    @Column(name = "content",length = 5000)
    private String content; // Nội dung thông báo
    @Column(name = "type")
    private String type; // Loại thông báo (VD: HP - Học phí, BB - Học bù, ...)
    @Column(name = "member")
    private String member; // Đối tượng nhận thông báo (VD: SV - Sinh viên)
    @Column(name = "dated")
    private LocalDateTime dated; // Thời gian thông báo được tạo
    @Column(name = "hocky")
    private int hocky; // Học kỳ
    @Column(name = "namhoc")
    private int namhoc; // Năm học

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
