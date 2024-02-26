package org.aston.ems.user_service.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.aston.ems.user_service.dto.UserDTO;
import org.aston.ems.user_service.service.CustomDigitComparator;
import org.aston.ems.user_service.service.CustomDigitComparatorReverse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/leaderboard", produces = "application/json")
public class LeaderboardController {

    @Value("${value.URI}")
    private String URI; //Заменить на endpoint когда он будет написан
    private final RestTemplate template = new RestTemplate();

    @GetMapping("/top")
    public List<UserDTO> getRatingBestStudent(@RequestParam int start, @RequestParam int end) {
        return getRatingStudent(new CustomDigitComparatorReverse(), start, end);
    }

    @GetMapping("/worst")
    public List<UserDTO> getRatingWorstStudent(@RequestParam int start, @RequestParam int end) {
        return getRatingStudent(new CustomDigitComparator(), start, end);
    }

    @SneakyThrows

    private List<UserDTO> getRatingStudent(Comparator<UserDTO> comparator, int start, int end) {

        String jsonListStudent = template.getForObject(URI, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserDTO> list = objectMapper.readValue(jsonListStudent, new TypeReference<>(){});

        if (list.size() < end)
            end = list.size() - 1;

        if (start == 0)
            start++;

        list.sort(comparator);
        list = list.subList(start -1, end);

        return list;
    }
}
