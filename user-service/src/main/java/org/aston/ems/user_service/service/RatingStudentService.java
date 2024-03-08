package org.aston.ems.user_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.aston.ems.user_service.dto.StudentProgressDataDTO;
import org.aston.ems.user_service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RatingStudentService {
    private String jsonListStudent;
    private ObjectMapper objectMapper;
    private String URI;
    private RestTemplate restTemplate;
    public RatingStudentService() {}
    @Autowired
    public RatingStudentService(String URI, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.URI = URI;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }
    @PostConstruct
    public void init() {
        this.jsonListStudent  = restTemplate.getForObject(URI, String.class);
    }

    /**
     *
     * @param comparator accept a comparator for compiling a list
     * @param start Starting position for output from the List (from which array element we start displaying)
     * @param end The final position for output from the List, it also limits the number of output
     * students in the List (we limit ourselves to which element of the array,
     * the maximum number is equal to the number of array elements)
     * @return Returns List<UserDTO> in JSON format
     * @throws JsonProcessingException may throw an error when parsing a JSON object into a sheet
     */

    public List<UserDTO> getRatingStudent(Comparator<UserDTO> comparator, int start, int end) throws JsonProcessingException {

        List<UserDTO>list = getListUserDTOForParseJsonListStudent();

        list.sort(comparator);

        list = list.subList((getStartIndex(start)-1), getSizeArrayIfLastElementIsLarger(list.size(),end));

        return list;
    }

    private List<UserDTO> getListUserDTOForParseJsonListStudent() throws JsonProcessingException {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<StudentProgressDataDTO> list = objectMapper.readValue(jsonListStudent, new TypeReference<>(){});

        for (StudentProgressDataDTO student : list) {
            userDTOList.add(new UserDTO(student.getNickname(), student.getTasks().get(0).getMark()));
        }

        return userDTOList;
    }
    private int getSizeArrayIfLastElementIsLarger(int listSize, int end) {

        if (listSize < end) {
            end = listSize;
        }
        return end;
    }
    private int getStartIndex(int start) {

        if (start == 0) {
            start++;
        }
        return start;
    }
}
