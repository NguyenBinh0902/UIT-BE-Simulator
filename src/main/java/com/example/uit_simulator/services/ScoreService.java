package com.example.uit_simulator.services;

import com.example.uit_simulator.dtos.responses.ScoreResponse;
import com.example.uit_simulator.models.Score;
import com.example.uit_simulator.models.User;
import com.example.uit_simulator.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public ResponseEntity<?> getStudentScores() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        System.out.println(currentUser.getStudent().getId());
        // Lấy danh sách điểm của sinh viên
        List<Score> scores = scoreRepository.findByStudentId(currentUser.getStudent().getId());

        if (scores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<String, List<Score>> groupedScores = scores.stream()
                .collect(Collectors.groupingBy(score -> score.getCourse().getHocky() + " - " + score.getCourse().getNamhoc()));

        // Tìm năm học lớn nhất
        OptionalInt maxYear = scores.stream()
                .mapToInt(score -> score.getCourse().getNamhoc())
                .max();

        String currentAcademicYear = maxYear.isPresent() ? String.valueOf(maxYear.getAsInt()) : "N/A";

        List<ScoreResponse> responses = new ArrayList<>();

        for (Map.Entry<String, List<Score>> entry : groupedScores.entrySet()) {
            ScoreResponse response = new ScoreResponse();
            response.setName("Điểm: HK " + entry.getValue().get(0).getCourse().getHocky() + ", NH " + entry.getValue().get(0).getCourse().getNamhoc() + "-" + (entry.getValue().get(0).getCourse().getNamhoc() + 1));
            response.setScore(entry.getValue().stream().map(this::mapToScoreDetails).collect(Collectors.toList()));
            response.setCurrentAcademicYear(currentAcademicYear);

            responses.add(response);
        }

        return ResponseEntity.ok(responses);
    }

    private ScoreResponse.ScoreDetails mapToScoreDetails(Score score) {
        ScoreResponse.ScoreDetails details = new ScoreResponse.ScoreDetails();
        details.setMamh(score.getCourse().getMamh());
        details.setMalop(score.getCourse().getMalop());
        details.setDiem(score.getDiem());
        details.setDiem1(score.getDiem1());
        details.setDiem2(score.getDiem2());
        details.setDiem3(score.getDiem3());
        details.setDiem4(score.getDiem4());
        details.setHeso1(score.getHeso1());
        details.setHeso2(score.getHeso2());
        details.setHeso3(score.getHeso3());
        details.setHeso4(score.getHeso4());
        details.setHocky(score.getCourse().getHocky());
        details.setNamhoc(score.getCourse().getNamhoc());
        details.setSotc(score.getCourse().getSotc());
        details.setTenmh(score.getCourse().getTenmh());
        details.setLoaimh(score.getCourse().getLoaimh());
        return details;
    }
}
