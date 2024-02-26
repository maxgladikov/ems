package org.aston.ems.user_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.ems.user_service.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Comparator;
import java.util.List;
@Service
public class RatingStudentService {
    private String URI;

    private final RestTemplate template = new RestTemplate();

    public RatingStudentService() {}

    public RatingStudentService(String uri) {
        URI = uri;
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

        String jsonListStudent = template.getForObject(URI, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserDTO> list = objectMapper.readValue(jsonListStudent, new TypeReference<>(){});

        if (list.size() < end)
            end = list.size();

        if (start == 0)
            start++;

        list.sort(comparator);
        list = list.subList(start -1, end);

        return list;
    }
}
