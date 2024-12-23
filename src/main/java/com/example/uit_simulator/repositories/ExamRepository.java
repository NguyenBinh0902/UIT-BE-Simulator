package com.example.uit_simulator.repositories;

import com.example.uit_simulator.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("SELECT e FROM Exam e WHERE e.examType = :examType AND e.course.hocky = :semester AND e.course.namhoc = :year AND e.course.id IN :courseIds")
    List<Exam> findExamsByCriteria(@Param("examType") String examType,
                                   @Param("semester") int semester,
                                   @Param("year") int year,
                                   @Param("courseIds") List<Long> courseIds);
}
