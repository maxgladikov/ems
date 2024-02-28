package org.aston.ems.user_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.ems.user_service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Comparator;
import java.util.List;

@Service
public class RatingStudentService {
    private String jsonListStudent;
    @Autowired
    private ObjectMapper objectMapper;

    public RatingStudentService() {}
    @Autowired
    public RatingStudentService(String URI, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.jsonListStudent  = restTemplate.getForObject(URI, String.class);
        this.objectMapper = objectMapper;
    }

    public List<UserDTO> getRatingStudent(Comparator<UserDTO> comparator, int start, int end) throws JsonProcessingException {

        List<UserDTO> list = getListUserDTOForParseJsonListStudent();

        list.sort(comparator);

        list = list.subList((ifFirstElementIsZero(start)-1), ifLastElementIsLargerSizeOfArray(list.size(),end));

        return list;
    }

    private List<UserDTO> getListUserDTOForParseJsonListStudent() throws JsonProcessingException {
        return objectMapper.readValue(jsonListStudent, new TypeReference<>(){});
    }
    private int ifLastElementIsLargerSizeOfArray(int listSize, int end) {

        if (listSize < end) end = listSize;
        return end;
    }
    private int ifFirstElementIsZero (int start) {

        if (start == 0) start++;
        return start;
    }
}
