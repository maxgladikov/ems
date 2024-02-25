package org.aston.ems.user_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.aston.ems.user_service.dto.UserDTO;
import org.aston.ems.user_service.service.CustomDigitComparator;
import org.aston.ems.user_service.service.CustomDigitComparatorReverse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/leaderboard", produces = "application/json")
public class LeaderboardController {
    private List<UserDTO> leaders;
    private final String URI = "http://localhost:9002/api/v1/student/task"; //Заменить на endpoint когда он будет написан
    private RestTemplate template = new RestTemplate();

    @GetMapping("/top")
    public List<UserDTO> getRatingBestStudent() throws JsonProcessingException {

        CustomDigitComparatorReverse comparatorLeader = new CustomDigitComparatorReverse();
        leaders = new ArrayList<>();

        String jsonListStudent = template.getForObject(URI, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        leaders = objectMapper.readValue(jsonListStudent, new TypeReference<>(){});

        leaders.sort(comparatorLeader);
        leaders = leaders.subList(0,3);

        return leaders;
    }

    @GetMapping("/antitop")
    public List<UserDTO> getRatingWorstStudent() throws JsonProcessingException {

        CustomDigitComparator comparatorWorst = new CustomDigitComparator();
        leaders = new ArrayList<>();

        String jsonListStudent = template.getForObject(URI, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        leaders = objectMapper.readValue(jsonListStudent, new TypeReference<>(){});

        leaders.sort(comparatorWorst);
        leaders = leaders.subList(0,3);

        return leaders;
    }

}
