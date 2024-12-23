package com.example.uit_simulator.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private String id; // ID của thông báo
    private String title; // Tiêu đề thông báo
    private String sid; // Mã số sinh viên
    private String content; // Nội dung thông báo
    private String type; // Loại thông báo
    private String member; // Đối tượng nhận thông báo
    private String dated; // Thời gian thông báo
    private String hocky; // Học kỳ
    private String namhoc; // Năm học
}
