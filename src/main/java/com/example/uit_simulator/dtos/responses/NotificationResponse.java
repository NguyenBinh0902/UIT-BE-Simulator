package com.example.uit_simulator.dtos.responses;

import com.example.uit_simulator.models.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private Long id; // ID của thông báo
    private String title; // Tiêu đề thông báo
    private String sid; // Mã số sinh viên
    private String content; // Nội dung thông báo
    private String type; // Loại thông báo
    private String member; // Đối tượng nhận thông báo
    private LocalDateTime dated; // Thời gian thông báo
    private int hocky; // Học kỳ
    private int namhoc; // Năm học

    public NotificationResponse toDTO(Notification notification) {
        this.id = notification.getId();
        this.title = notification.getTitle();
        this.sid = notification.getStudent().getSid();
        this.content = notification.getContent();
        this.type = notification.getType();
        this.member = notification.getMember();
        this.dated = notification.getDated();
        this.hocky = notification.getHocky();
        this.namhoc = notification.getNamhoc();
        return this;
    }
}
