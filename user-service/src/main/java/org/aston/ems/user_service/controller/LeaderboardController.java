package org.aston.ems.user_service.controller;

import org.aston.ems.user_service.dto.UserDTO;
import org.aston.ems.user_service.service.CustomDigitComparator;
import org.aston.ems.user_service.service.CustomDigitComparatorReverse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/leaderboard", produces = "application/json")
public class LeaderboardController {
    private List<UserDTO> leaders;
    @GetMapping("/top")
    public List<UserDTO> getRatingBestStudent(){
        CustomDigitComparatorReverse comparatorLeader = new CustomDigitComparatorReverse();

        leaders = new ArrayList<>();
        leaders.add(new UserDTO("name1", 20));
        leaders.add(new UserDTO("name2", 8));
        leaders.add(new UserDTO("name3", 10));
        leaders.add(new UserDTO("name4", 15));
        leaders.add(new UserDTO("name5", 1));

        leaders.sort(comparatorLeader);

        return leaders;
    }

    @GetMapping("/antitop")
    public List<UserDTO> getRatingWorstStudent(){
        CustomDigitComparator comparatorLeader = new CustomDigitComparator();

        leaders = new ArrayList<>();
        leaders.add(new UserDTO("name1", 20));
        leaders.add(new UserDTO("name2", 8));
        leaders.add(new UserDTO("name3", 10));
        leaders.add(new UserDTO("name4", 15));
        leaders.add(new UserDTO("name5", 1));

        leaders.sort(comparatorLeader);

        return leaders;
    }

}
//    Узнать лучших учеников get localhost:port/api/v1/leaderboard/top/вернуть всех студентов(по возрастанию оценки)
//        Узнать худших учеников get localhost:port/api/v1/leaderboard/antitop/вернуть всех студентов(по возрастанию оценки)
