package com.example.uit_simulator.repositories;

import com.example.uit_simulator.models.SpecialSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialScheduleRepository extends JpaRepository<SpecialSchedule, Long> {
    List<SpecialSchedule> findSpecialSchedulesByCourseId(Long id);
}
