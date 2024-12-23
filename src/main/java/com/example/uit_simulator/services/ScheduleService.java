package com.example.uit_simulator.services;

import com.example.uit_simulator.dtos.responses.LecturerResponse;
import com.example.uit_simulator.dtos.responses.ScheduleResponse;
import com.example.uit_simulator.models.Course;
import com.example.uit_simulator.models.Schedule;
import com.example.uit_simulator.models.SpecialSchedule;
import com.example.uit_simulator.models.User;
import com.example.uit_simulator.repositories.CourseRepository;
import com.example.uit_simulator.repositories.ScheduleRepository;
import com.example.uit_simulator.repositories.SpecialScheduleRepository;
import com.example.uit_simulator.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final SpecialScheduleRepository specialScheduleRepository;

//    public ResponseEntity<List<ScheduleResponse>> getSchedulesForStudent() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = (User) authentication.getPrincipal();
//        if (currentUser.getStudent() == null) {
//            throw new RuntimeException("User is not a student");
//        }
//        List<Schedule> schedules = scheduleRepository.findSchedulesByStudentId(currentUser.getStudent().getId());
//
//        return ResponseEntity.ok(schedules.stream()
//                .map(this::convertToScheduleResponse)
//                .collect(Collectors.toList()));
//    }

    public ResponseEntity<List<ScheduleResponse>> getWeeklySchedule(int hocky, int namhoc) {
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if (currentUser.getStudent() == null) {
            throw new RuntimeException("User is not a student");
        }
        // Lấy danh sách khóa học của sinh viên trong học kỳ và năm học
        List<Course> courses = courseRepository.findCoursesByStudentIdAndSemester(currentUser.getStudent().getId(), hocky, namhoc);

        // Lấy ngày bắt đầu và kết thúc tuần hiện tại
        LocalDate weekStartDate = LocalDate.now().with(DayOfWeek.MONDAY); // Ngày bắt đầu tuần (Thứ 2)
        LocalDate weekEndDate = weekStartDate.plusDays(6);      // Ngày kết thúc tuần (Chủ Nhật)

        // Danh sách kết quả
        List<ScheduleResponse> weeklySchedule = new ArrayList<>();

        for (Course course : courses) {
            Schedule schedule = scheduleRepository.findScheduleByCourseId(course.getId()).orElseThrow(() -> new RuntimeException("Schedule not found"));
            List<SpecialSchedule> specialSchedules = specialScheduleRepository.findSpecialSchedulesByCourseId(course.getId());

            // Lặp qua từng ngày trong tuần
            for (LocalDate date = weekStartDate; !date.isAfter(weekEndDate); date = date.plusDays(1)) {
                DayOfWeek dayOfWeek = date.getDayOfWeek();

                // Kiểm tra lịch đặc biệt trước
                LocalDate finalDate = date;
                Optional<SpecialSchedule> specialSchedule = specialSchedules.stream()
                        .filter(ss -> ss.getNgay().isEqual(finalDate))
                        .findFirst();

                if (specialSchedule.isPresent()) {
                    // Nếu có lịch đặc biệt
                    weeklySchedule.add(convertToScheduleResponse(specialSchedule.get(), date));
                } else if (isWithinDefaultSchedule(schedule, date, dayOfWeek)) {
                    // Nếu không có lịch đặc biệt, kiểm tra lịch mặc định
                    weeklySchedule.add(convertToScheduleResponse(schedule, date));
                }
            }
        }

        return ResponseEntity.ok(weeklySchedule);
    }

    private boolean isWithinDefaultSchedule(Schedule schedule, LocalDate date, DayOfWeek dayOfWeek) {
        return dayOfWeek.getValue() == Integer.parseInt(schedule.getThu()) &&
                !date.isBefore(schedule.getNgaybd()) &&
                !date.isAfter(schedule.getNgaykt());
    }

    private ScheduleResponse convertToScheduleResponse(SpecialSchedule specialSchedule, LocalDate date) {
        ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setThu(date.getDayOfWeek().getValue());
        scheduleResponse.setPhonghoc(specialSchedule.getPhonghoc());
        scheduleResponse.setOnline(specialSchedule.isOnline());
        scheduleResponse.setTiet(specialSchedule.getTiet());

        return scheduleResponse;
    }

    private ScheduleResponse convertToScheduleResponse(Schedule schedule, LocalDate date) {
        ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setThu(date.getDayOfWeek().getValue());
        scheduleResponse.setPhonghoc(schedule.getPhonghoc());
        scheduleResponse.setOnline(schedule.isOnline());
        scheduleResponse.setTiet(schedule.getTiet());
        scheduleResponse.setDadk(schedule.getCourse().getDadangky());
        scheduleResponse.setHinhthucgd(schedule.getCourse().getHinhthucgd());
        scheduleResponse.setKhoaql(schedule.getCourse().getKhoaql());
        scheduleResponse.setTenmh(schedule.getCourse().getTenmh());
        scheduleResponse.setMamon(schedule.getCourse().getMamh());
        scheduleResponse.setMalop(schedule.getCourse().getMalop());
        scheduleResponse.setSotc(schedule.getCourse().getSotc());
        scheduleResponse.setLoaimh(schedule.getCourse().getLoaimh());
        scheduleResponse.setHt2_lichgapsv(schedule.getCourse().getHt2_lichgapsv());
        scheduleResponse.setNgonngu(schedule.getCourse().getNgonngu());
        scheduleResponse.setHocky(schedule.getCourse().getHocky());
        scheduleResponse.setNamhoc(schedule.getCourse().getNamhoc());
        scheduleResponse.setNgaybd(schedule.getNgaybd());
        scheduleResponse.setNgaykt(schedule.getNgaykt());
        scheduleResponse.setMagv(new LecturerResponse().mapLecturersToDTO(schedule.getCourse().getLecturers()));

        return scheduleResponse;
    }

}
