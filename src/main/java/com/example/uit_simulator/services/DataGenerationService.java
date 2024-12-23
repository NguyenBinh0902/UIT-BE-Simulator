package com.example.uit_simulator.services;


import com.example.uit_simulator.enums.RoleName;
import com.example.uit_simulator.models.*;
import com.example.uit_simulator.repositories.*;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class DataGenerationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;
    private final ScheduleRepository scheduleRepository;
    private final SpecialScheduleRepository specialScheduleRepository;
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;
    private final ExamRepository examRepository;
    private final NotificationRepository notificationRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    public void generateUserData(RoleName role, int amount) {
        List<User> users = new ArrayList<>();
        Role existingRole = roleRepository.findByName(role).orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        for (int i = 0; i < amount; i++) {
            User user = new User();

            if (role == RoleName.SV) {
                // Tạo username và email
                String code = "2152" + faker.number().digits(4);
                user.setUsername(code);
                user.setEmail(code + "@gm.uit.edu.vn");
                user.setPassword(passwordEncoder.encode("123456"));
                user.getRoles().add(existingRole);

                // Tạo Student
                Student student = new Student();
                student.setSid(code);
                student.setUser(user); // Liên kết student với user
                student.setCourse(faker.number().numberBetween(1, 10)); // Random course

                // Tạo Profile
                Profile profile = new Profile();
                profile.setName(faker.name().fullName());
                profile.setEmail(user.getEmail());
                profile.setStatus(faker.number().numberBetween(1, 4)); // Random status
                profile.setMajor("Kỹ Thuật Phần Mềm"); // Mặc định là Kỹ Thuật Phần Mềm
                profile.setDob(faker.date().birthday(18, 25).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                profile.setRole("SV");
                profile.setClassName("CNPM" + faker.number().digits(2)); // Ví dụ class: CNPM01
                profile.setAddress(faker.address().fullAddress());
                profile.setAvatarUrl(faker.internet().avatar());

                student.setProfile(profile); // Liên kết profile với student

                // Liên kết lại các đối tượng
                student.setUser(user);
                user.setStudent(student);

                // Thêm user vào danh sách
                users.add(user);
            } else if (role == RoleName.GV) {
                // Tạo username và email
                String code = "GV" + faker.number().digits(4);
                user.setUsername(code);
                user.setEmail(code + "@gm.uit.edu.vn");
                user.setPassword(passwordEncoder.encode("123456"));
                user.getRoles().add(existingRole);

                // Tạo Lecturer
                Lecturer lecturer = new Lecturer();
                lecturer.setLecturerId(code);

                // Tạo Profile
                Profile profile = new Profile();
                profile.setName(faker.name().fullName());
                profile.setEmail(user.getEmail());
                profile.setStatus(faker.number().numberBetween(1, 4)); // Random status
                profile.setMajor("Kỹ Thuật Phần Mềm"); // Mặc định là Kỹ Thuật Phần Mềm
                profile.setDob(faker.date().birthday(18, 25).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                profile.setRole("SV");
                profile.setClassName("CNPM" + faker.number().digits(2)); // Ví dụ class: CNPM01
                profile.setAddress(faker.address().fullAddress());
                profile.setAvatarUrl(faker.internet().avatar());

                lecturer.setProfile(profile); // Liên kết profile với student

                // Liên kết lại các đối tượng
                lecturer.setUser(user);
                user.setLecturer(lecturer);

                // Thêm user vào danh sách
                users.add(user);
            }


        }

        // Lưu tất cả vào cơ sở dữ liệu
        userRepository.saveAll(users);
        System.out.println("Inserted fake users with students and profiles into the database.");
    }

    @Transactional
    public void generateScheduleData() {
        // Tạo dữ liệu giả cho Lecturer
        List<Lecturer> lecturers = lecturerRepository.findAll();

        // Tạo dữ liệu giả cho Course
        List<Course> courses = createCourses(20, lecturers);

        // Tạo dữ liệu giả cho Schedule và SpecialSchedule
        createSchedulesAndSpecialSchedules(courses);
    }

    private List<Course> createCourses(int count, List<Lecturer> lecturers) {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Course course = new Course();
            course.setMamh("SE" + faker.number().digits(3));
            course.setTenmh(faker.book().title());
            course.setMalop(course.getMamh() + ".PMCL" );
            String[] hinhThucGdOptions = {"LT", "HT2", "TH"};
            course.setHinhthucgd(hinhThucGdOptions[random.nextInt(hinhThucGdOptions.length)]);
            course.setKhoaql("CNTT");
            course.setSotc(random.nextInt(3) + 2);
            course.setHocky(1);
            course.setNamhoc(2023);
            course.setThuchanh(random.nextBoolean());
            course.setLoaimh("CSNN");
            course.setDadangky(random.nextInt(100));
            course.setNgonngu("VN");
            course.setHt2_lichgapsv("Tiết 3,4 ngày 2023-10-15, P B6.12");

            // Gán lecturer cho course
            course.setLecturers(lecturers.subList(0, random.nextInt(lecturers.size())));

            courses.add(course);
        }
        return courseRepository.saveAll(courses);
    }

    private void createSchedulesAndSpecialSchedules(List<Course> courses) {
        List<Schedule> schedules = new ArrayList<>();
        List<SpecialSchedule> specialSchedules = new ArrayList<>();

        for (Course course : courses) {
            // Tạo Schedule
            Schedule schedule = new Schedule();
            schedule.setThu(String.valueOf(random.nextInt(5) + 2)); // Thứ 2 -> Thứ 6
            schedule.setTiet((random.nextInt(4) + 1) + "-" + (random.nextInt(3) + 4)); // Tiết 1-3, 2-4, ...
            schedule.setPhonghoc("P" + faker.number().digits(3));
            schedule.setOnline(random.nextBoolean());
            schedule.setNgaybd(LocalDate.of(2023, 9, 2));
            schedule.setNgaykt(LocalDate.of(2023, 12, 28));
            schedule.setCourse(course);
            course.setSchedule(schedule);
            schedules.add(schedule);

            // Tạo SpecialSchedule (3 lịch đặc biệt)
            for (int i = 0; i < 3; i++) {
                SpecialSchedule specialSchedule = new SpecialSchedule();
                specialSchedule.setNgay(LocalDate.of(2023, 9, 15).plusDays(i * 10)); // Ngày cách nhau 10 ngày
                specialSchedule.setTiet("4-5");
                specialSchedule.setPhonghoc("P" + faker.number().digits(3));
                specialSchedule.setOnline(random.nextBoolean());
                specialSchedule.setLydo("Học bù");
                specialSchedule.setCourse(course);
                specialSchedules.add(specialSchedule);
            }
//            course.setSpecialSchedules(specialSchedules);
//            courseRepository.save(course);
        }
        scheduleRepository.saveAll(schedules);
        System.out.println("Inserted fake schedules and special schedules into the database.");
        specialScheduleRepository.saveAll(specialSchedules);
    }

    @Transactional
    public void assignCoursesToStudents() {
        // Lấy tất cả sinh viên
        List<Student> students = studentRepository.findAll();

        // Lấy tất cả các khóa học
        List<Course> courses = courseRepository.findAll();

        if (students.isEmpty() || courses.isEmpty()) {
            System.out.println("Không có dữ liệu Student hoặc Course trong cơ sở dữ liệu.");
            return;
        }

        Random random = new Random();

        for (Student student : students) {
            // Gán ngẫu nhiên từ 1-3 khóa học cho mỗi sinh viên
            int courseCount = random.nextInt(3) + 1; // 1 đến 3 khóa học

            for (int i = 0; i < courseCount; i++) {
                Course course = courses.get(random.nextInt(courses.size()));
                student.getCourses().add(course);
            }
        }

        // Lưu các thay đổi vào cơ sở dữ liệu
        studentRepository.saveAll(students);
        System.out.println("Successfully assigned courses to students.");
    }

    @Transactional
    public void generateFakeScores() {
        List<Score> scores = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            // Lấy ngẫu nhiên các khóa học mà sinh viên đã đăng ký
            List<Course> enrolledCourses = student.getCourses();

            for (Course course : enrolledCourses) {
                // Tạo điểm cho khóa học này
                Score score = new Score();
                score.setStudent(student);
                score.setCourse(course);

                // Tạo các điểm thành phần
                double diem1 = random.nextInt(3) + 7 + random.nextDouble(); // 7-10
                double diem2 = random.nextInt(3) + 7 + random.nextDouble(); // 7-10
                double diem3 = random.nextInt(3) + 7 + random.nextDouble(); // 7-10
                double diem4 = random.nextInt(3) + 7 + random.nextDouble(); // 7-10

                // Hệ số ngẫu nhiên
                double heso1 = 0.1;
                double heso2 = 0.2;
                double heso3 = 0.3;
                double heso4 = 0.4;

                // Tổng điểm = Tổng (điểm * hệ số)
                double totalScore = diem1 * heso1 + diem2 * heso2 + diem3 * heso3 + diem4 * heso4;

                // Set các giá trị vào Score
                score.setDiem(String.format("%.1f", totalScore));
                score.setDiem1(String.format("%.1f", diem1));
                score.setDiem2(String.format("%.1f", diem2));
                score.setDiem3(String.format("%.1f", diem3));
                score.setDiem4(String.format("%.1f", diem4));

                score.setHeso1(String.valueOf(heso1));
                score.setHeso2(String.valueOf(heso2));
                score.setHeso3(String.valueOf(heso3));
                score.setHeso4(String.valueOf(heso4));

                scores.add(score);
            }
        }

        // Lưu toàn bộ điểm vào cơ sở dữ liệu
        scoreRepository.saveAll(scores);
        System.out.println("Fake scores have been successfully generated.");
    }

    @Transactional
    public void createFakeExams() {
        List<Course> courses = courseRepository.findAll(); // Lấy danh sách tất cả các khóa học

        if (courses.isEmpty()) {
            System.out.println("Không có khóa học nào trong cơ sở dữ liệu. Hãy tạo dữ liệu khóa học trước.");
            return;
        }

        List<Exam> exams = new ArrayList<>();

        for (Course course : courses) {
            // Giả định mỗi môn học có 2 kỳ thi: giữa kỳ và cuối kỳ
            for (String examType : List.of("GK", "CK")) {
                Exam exam = new Exam();

                // Gán thông tin kỳ thi
                exam.setCourse(course);
                exam.setExamType(examType);
                exam.setSemester(course.getHocky());
                exam.setYear(course.getNamhoc());

                // Tạo dữ liệu ngày thi
                LocalDate examDate = course.getHocky() == 1
                        ? LocalDate.of(course.getNamhoc(), 10, 1).plusDays(random.nextInt(30))
                        : LocalDate.of(course.getNamhoc(), 4, 1).plusDays(random.nextInt(30));
                exam.setDate(examDate);

                // Tạo dữ liệu ca thi
                int shiftNumber = random.nextInt(3) + 1; // Ca 1, 2 hoặc 3
                exam.setShift("Ca " + shiftNumber + "(" + (7 + shiftNumber * 2) + "h30)");

                // Tạo dữ liệu phòng thi
                exam.setRoom("P" + random.nextInt(20) + 1);

                exams.add(exam);
            }
        }

        examRepository.saveAll(exams);
        System.out.println("Đã tạo dữ liệu fake cho các kỳ thi.");
    }

    @Transactional
    public void createFakeNotifiesForStudents() {
        List<Notification> notifications = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        Random random = new Random();

        // Ví dụ tạo dữ liệu thông báo riêng cho các sinh viên
        String[] titles = {"Thông báo học phí", "Thông báo lịch học bù", "Thông báo điểm thi", "Thông báo đăng ký môn học"};
        String[] contents = {
                "Chào sinh viên, vui lòng hoàn tất học phí trước ngày 31/08.",
                "Lịch học bù sẽ được tổ chức vào ngày 15/06 tại phòng B2.10.",
                "Điểm thi học kỳ 2 đã được cập nhật. Vui lòng kiểm tra cổng thông tin.",
                "Học kỳ mới bắt đầu đăng ký từ ngày 20/07."
        };
        String[] types = {"HP", "BB", "DT", "DK"};

        for (Student student : students) {
            for (int i = 0; i < 5; i++) { // Mỗi sinh viên nhận 5 thông báo
                Notification notification = new Notification();
                notification.setTitle(titles[random.nextInt(titles.length)]);
                notification.setStudent(student);
                notification.setContent(contents[random.nextInt(contents.length)]);
                notification.setType(types[random.nextInt(types.length)]);
                notification.setMember("SV");
                notification.setDated(LocalDateTime.now().minusDays(random.nextInt(30))); // Tạo ngẫu nhiên ngày trong 30 ngày qua
                notification.setHocky(1); // Học kỳ mặc định
                notification.setNamhoc(faker.random().nextInt(2023, 2024));
                notifications.add(notification);
            }
        }

        notificationRepository.saveAll(notifications);
        System.out.println("Fake data for personal notifications created successfully!");
    }
}