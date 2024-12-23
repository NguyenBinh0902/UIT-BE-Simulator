package com.example.uit_simulator.repositories;

import com.example.uit_simulator.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId AND c.hocky = :hocky AND c.namhoc = :namhoc")
    List<Course> findCoursesByStudentIdAndSemester(
            @Param("studentId") Long studentId,
            @Param("hocky") int hocky,
            @Param("namhoc") int namhoc
    );
}
