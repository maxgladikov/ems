package org.aston.ems.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/leaderboard", produces = "application/json")
public class LeaderboardController {

    @GetMapping("/top")
    ResponseEntity<String> doTop(){
        return ResponseEntity.ok()
                .body("Hello! top");
    }

    @GetMapping("/antitop")
    ResponseEntity<String> doAntitop(){
        return ResponseEntity.ok()
                .body("Hello! anti");
    }

}
//    Узнать лучших учеников get localhost:port/api/v1/leaderboard/top/вернуть всех студентов(по возрастанию оценки)
//        Узнать худших учеников get localhost:port/api/v1/leaderboard/antitop/вернуть всех студентов(по возрастанию оценки)
