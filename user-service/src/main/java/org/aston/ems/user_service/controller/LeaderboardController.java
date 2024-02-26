package org.aston.ems.user_service.controller;


import lombok.SneakyThrows;
import org.aston.ems.user_service.dto.UserDTO;
import org.aston.ems.user_service.service.CustomDigitComparator;
import org.aston.ems.user_service.service.CustomDigitComparatorReverse;
import org.aston.ems.user_service.service.RatingStudent;
import org.aston.ems.user_service.service.RatingStudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/leaderboard", produces = "application/json")
public class LeaderboardController {

    @Value("${value.URI}")
    private String URI; //Заменить на endpoint когда он будет написан в application.yml


    @SneakyThrows
    @GetMapping("/top")
    public List<UserDTO> getRatingBestStudent(@RequestParam int start, @RequestParam int end) {
        return new RatingStudentService(URI).getRatingStudent(new CustomDigitComparatorReverse(), start, end);
    }

    @SneakyThrows
    @GetMapping("/worst")
    public List<UserDTO> getRatingWorstStudent(@RequestParam int start, @RequestParam int end) {
        return new RatingStudentService(URI).getRatingStudent(new CustomDigitComparator(), start, end);
    }


}
