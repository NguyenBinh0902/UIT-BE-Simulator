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
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> getPersonalNotifies( int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dated")); // Sắp xếp từ mới đến cũ
        List<Notification> notifications = notificationRepository.findByStudentId(user.getStudent().getId(), pageable);

        return notifications.stream().map(notification -> new NotificationResponse().toDTO(notification)).toList();
    }
}
