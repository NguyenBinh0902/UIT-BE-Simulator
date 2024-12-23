package com.example.uit_simulator.repositories;

import com.example.uit_simulator.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByThuAndCourse_Malop(String thu, String malop);

    List<Schedule> findByThu(String thu);
    @Query("SELECT s FROM Schedule s JOIN s.course c JOIN c.students st WHERE st.id = :studentId AND s.thu = :thu")
    List<Schedule> findSchedulesByStudentIdAndThu(@Param("studentId") Long studentId, @Param("thu") String thu);

    @Query("SELECT s FROM Schedule s JOIN s.course c JOIN c.students st WHERE st.id = :studentId")
    List<Schedule> findSchedulesByStudentId(@Param("studentId") Long studentId);

    Optional<Schedule> findScheduleByCourseId(Long id);
}
