package com.example.uit_simulator.repositories;

import com.example.uit_simulator.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
//    List<Score> findByStudentIdAndHockyAndNamhoc(Long studentId, int hocky, int namhoc);
    List<Score> findByStudentId(Long studentId);
}
