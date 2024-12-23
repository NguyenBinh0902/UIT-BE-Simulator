package com.example.uit_simulator.services;

import com.example.uit_simulator.dtos.responses.NotificationResponse;
import com.example.uit_simulator.models.Notification;
import com.example.uit_simulator.models.User;
import com.example.uit_simulator.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Page<NotificationResponse> getPersonalNotifies( int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dated")); // Sắp xếp từ mới đến cũ
        Page<Notification> notificationPage = notificationRepository.findByStudentId(user.getStudent().getId(), pageable);

        // Chuyển đổi từ Page<Notify> sang Page<NotifyResponse>
        return notificationPage.map(notification -> {
            NotificationResponse response = new NotificationResponse();
            response.setId(String.valueOf(notification.getId()));
            response.setTitle(notification.getTitle());
            response.setSid(notification.getStudent().getSid());
            response.setContent(notification.getContent());
            response.setType(notification.getType());
            response.setMember(notification.getMember());
            response.setDated(notification.getDated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            response.setHocky(String.valueOf(notification.getHocky()));
            response.setNamhoc(String.valueOf(notification.getNamhoc()));
            return response;
        });
    }
}
