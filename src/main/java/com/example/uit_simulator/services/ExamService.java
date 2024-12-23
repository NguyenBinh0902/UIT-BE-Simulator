package com.example.uit_simulator.services;

import com.example.uit_simulator.dtos.responses.ExamResponse;
import com.example.uit_simulator.models.Course;
import com.example.uit_simulator.models.Exam;
import com.example.uit_simulator.models.User;
import com.example.uit_simulator.repositories.ExamRepository;
import com.example.uit_simulator.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    public List<ExamResponse> getExamResponses(String examType, int semester, int year) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long studentId = user.getStudent().getId();
        // Lấy danh sách các khóa học mà sinh viên đang học
        List<Course> studentCourses = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"))
                .getCourses();

        // Lấy danh sách các courseId
        List<Long> courseIds = studentCourses.stream()
                .map(Course::getId)
                .collect(Collectors.toList());

        if (courseIds.isEmpty()) {
            return Collections.emptyList(); // Nếu không có khóa học, trả về danh sách rỗng
        }

        // Lấy danh sách các Exam dựa trên tiêu chí
        List<Exam> exams = examRepository.findExamsByCriteria(examType, semester, year, courseIds);

        // Map Exam sang ExamResponse
        return exams.stream().map(exam -> {
            ExamResponse response = new ExamResponse();

            // Lấy thông tin từ khóa học
            Course course = exam.getCourse();
            response.setMamh(course.getMamh()); // Mã môn học
            response.setMalop(course.getMalop()); // Mã lớp

            // Đặt các thông tin của lịch thi
            response.setCaTietThi(exam.getShift()); // Ca/Tiết thi
            response.setThuThi("Thứ " + exam.getDate().getDayOfWeek().getValue()); // Thứ thi
            response.setNgayThi(exam.getDate()); // Ngày thi
            response.setPhongThi(exam.getRoom()); // Phòng thi
            response.setGhiChu("Hình thức thi: Offline"); // Ghi chú hoặc hình thức thi

            return response;
        }).collect(Collectors.toList());
    }

}
