package com.example.uit_simulator.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.uit_simulator.models.Notification;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStudentIdAndHockyAndNamhoc(Long studentId, int hocky, int namhoc);
    Page<Notification> findByStudentId(Long studentId, Pageable pageable);
}
