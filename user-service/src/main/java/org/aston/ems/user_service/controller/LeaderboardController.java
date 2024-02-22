package org.aston.ems.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/leaderboard", produces = "application/json")
public class LeaderboardController {
}
//    Узнать лучших учеников get localhost:port/api/v1/leaderboard/top/вернуть всех студентов(по возрастанию оценки)
//        Узнать худших учеников get localhost:port/api/v1/leaderboard/antitop/вернуть всех студентов(по возрастанию оценки)
