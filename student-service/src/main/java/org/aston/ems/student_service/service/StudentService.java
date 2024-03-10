package org.aston.ems.student_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.aston.ems.student_service.dto.*;
import org.aston.ems.student_service.exception.ResourceNotFoundException;
import org.aston.ems.student_service.mapper.StudentMapper;
import org.aston.ems.student_service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private static final String STUDENT_ROLE = "STUDENT";

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final ObjectMapper objectMapper;

    public List<StudentProgressDataDTO> getAllWithProgressData() {
        var students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::mapToProgressData)
                .toList();
    }

    public void download() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://host.docker.internal:9000/api/v1/admin/internal/users";
        String response = restTemplate.getForObject(resourceUrl, String.class);

        List<UserDTO> users = objectMapper.readValue(response, new TypeReference<List<UserDTO>>() {});
        for (var user: users) {
            if (user.getAuthorities().get(0).equals(STUDENT_ROLE)) {
                var studentCreateDTO = new StudentCreateDTO();
                studentCreateDTO.setNickname(user.getName());
                var student = studentMapper.map(studentCreateDTO);
                studentRepository.save(student);
            }
        }
    }

    public List<StudentDTO> getAll() {
        var students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::map)
                .toList();
    }

    public StudentDTO create(StudentCreateDTO userData) {
        var student = studentMapper.map(userData);
        studentRepository.save(student);
        return studentMapper.map(student);
    }

    public StudentDTO findById(Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id %s not found", id)));
        return studentMapper.map(student);
    }

    public StudentDTO update(StudentUpdateDTO userData, Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id %s not found", id)));
        studentMapper.update(userData, student);
        studentRepository.save(student);
        return studentMapper.map(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
