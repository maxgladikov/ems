package org.aston.ems.user_service.controller;


import lombok.SneakyThrows;
import org.aston.ems.user_service.dto.UserDTO;
import org.aston.ems.user_service.service.CustomDigitComparator;
import org.aston.ems.user_service.service.CustomDigitComparatorReverse;
import org.aston.ems.user_service.service.RatingStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/leaderboard", produces = "application/json")
public class LeaderboardController {

    private final RatingStudentService ratingStudentService;
    @Autowired
    public LeaderboardController(RatingStudentService ratingStudentService){
            this.ratingStudentService = ratingStudentService;
    }

    @SneakyThrows
    @GetMapping("/top")
    public ResponseEntity<List<UserDTO>> getRatingBestStudent(@RequestParam int start, @RequestParam int end) {

        List<UserDTO> list = ratingStudentService.getRatingStudent(new CustomDigitComparatorReverse(), start, end);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/worst")
    public ResponseEntity<List<UserDTO>> getRatingWorstStudent(@RequestParam int start, @RequestParam int end) {

        List<UserDTO> list = ratingStudentService.getRatingStudent(new CustomDigitComparator(), start, end);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
