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
    @Column(name = "mamh")
    private String mamh; // Mã lớp học
    @Column(name = "tenmh")
    private String tenmh; // Tên môn học
    @Column(name = "malop")
    private String malop; // Phòng học
    @Column(name = "hinhthucgd")
    private String hinhthucgd; // Hình thức giảng dạy (LT/HT2)
    @Column(name = "khoaql")
    private String khoaql; // Khoa quản lý
    @Column(name = "sotc")
    private int sotc; // Số tín chỉ
    @Column(name = "hocky")
    private int hocky; // Học kỳ
    @Column(name = "thuchanh")
    private boolean thuchanh; // Thực hành
    @Column(name = "namhoc")
    private int namhoc; // Năm học
    @Column(name = "loaimh")
    private String loaimh; // Loại môn học
    @Column(name = "dadangky")
    private int dadangky; // Đã đăng ký
    @Column(name = "ngonngu")
    private String ngonngu; // Ngôn ngữ
    @Column(name = "ht2_lichgapsv")
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
