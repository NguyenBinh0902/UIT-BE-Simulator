package com.example.uit_simulator.repositories;

import com.example.uit_simulator.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE s.id = :studentId AND c.malop = :malop")
    Optional<Student> findStudentInCourse(@Param("studentId") Long studentId, @Param("malop") String malop);
}
