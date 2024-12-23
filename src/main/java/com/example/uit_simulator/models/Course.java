package com.example.uit_simulator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mamh; // Mã lớp học
    private String tenmh; // Tên môn học
    private String malop; // Phòng học
    private String hinhthucgd; // Hình thức giảng dạy (LT/HT2)
    private String khoaql; // Khoa quản lý
    private int sotc; // Số tín chỉ
    private int hocky; // Học kỳ
    private boolean thuchanh; // Thực hành
    private int namhoc; // Năm học
    private String loaimh; // Loại môn học
    private int dadangky; // Đã đăng ký
    private String ngonngu; // Ngôn ngữ
    private String ht2_lichgapsv; // Lịch gặp sinh viên

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Schedule schedule;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SpecialSchedule> specialSchedules;

    @ManyToMany
    @JoinTable(
            name = "course_lecturer",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "lecturer_id")
    )
    private List<Lecturer> lecturers;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> students = new ArrayList<>();
}
