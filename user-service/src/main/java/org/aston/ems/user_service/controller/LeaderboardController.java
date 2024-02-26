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
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/leaderboard", produces = "application/json")
public class LeaderboardController {
    private final String URI = "http://localhost:9002/api/v1/student/task"; //Заменить на endpoint когда он будет написан
    private final RestTemplate template = new RestTemplate();

    @GetMapping("/top")
    public List<UserDTO> getRatingBestStudent() throws JsonProcessingException {
        return getRatingStudent(new ArrayList<>(), new CustomDigitComparatorReverse());
    }

    @GetMapping("/antitop")
    public List<UserDTO> getRatingWorstStudent() {
        return getRatingStudent(new ArrayList<>(), new CustomDigitComparator());
    }

    @SneakyThrows
    private List<UserDTO> getRatingStudent(List<UserDTO> list, Comparator<UserDTO> comparator) {

        String jsonListStudent = template.getForObject(URI, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        list = objectMapper.readValue(jsonListStudent, new TypeReference<>(){});

        list.sort(comparator);
        list = list.subList(0,3);
        return list;
    }
}
