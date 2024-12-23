package com.example.uit_simulator.dtos.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExamResponse {
    private String mamh;           // Mã môn học
    private String malop;          // Mã lớp
    private String caTietThi;      // Ca/Tiết thi
    private String thuThi;         // Thứ thi
    private LocalDate ngayThi;     // Ngày thi
    private String phongThi;       // Phòng thi
    private String ghiChu;         // Ghi chú/Hình thức thi
}
