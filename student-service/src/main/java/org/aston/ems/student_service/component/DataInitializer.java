package org.aston.ems.student_service.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.aston.ems.student_service.dto.StudentCreateDTO;
import org.aston.ems.student_service.dto.TaskCreateDTO;
import org.aston.ems.student_service.dto.TaskUpdateDTO;
import org.aston.ems.student_service.dto.UserDTO;
import org.aston.ems.student_service.mapper.StudentMapper;
import org.aston.ems.student_service.mapper.TaskMapper;
import org.aston.ems.student_service.model.Score;
import org.aston.ems.student_service.repository.StudentRepository;
import org.aston.ems.student_service.repository.TaskRepository;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private static final List<String> NICKNAMES = List.of("Ivan Ivanov", "Fedor Fedorov", "Oleg Olegov");

    private static final String STUDENT_ROLE = "STUDENT";

    private final StudentRepository studentRepository;

    private final TaskRepository taskRepository;

    private final StudentMapper studentMapper;

    private final TaskMapper taskMapper;

    private final ObjectMapper objectMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        getStudents();
        addDefaultStudents();
        addDefaultTask10();
        addDefaultTask11();
        addDefaultTask12();
    }

    public void addDefaultStudents() {
        for (var nickname: NICKNAMES) {
            addStudent(nickname);
        }
    }

    public void addStudent(String nickname) {
        var studentCreateDTO = new StudentCreateDTO();
        studentCreateDTO.setNickname(nickname);
        var student = studentMapper.map(studentCreateDTO);
        studentRepository.save(student);
    }

    public void addDefaultTask10() {
        var taskCreateDTO = new TaskCreateDTO();
        taskCreateDTO.setId(100L);
        taskCreateDTO.setNickname(NICKNAMES.get(0));
        taskCreateDTO.setContent("Important question");
        var task = taskMapper.map(taskCreateDTO);
        var assignee = studentRepository.findByNickname(NICKNAMES.get(0)).orElse(null);
        task.setAssignee(assignee);
        taskRepository.save(task);

        var taskUpdateDTO = new TaskUpdateDTO();
        taskUpdateDTO.setId(100L);
        taskUpdateDTO.setNickname(NICKNAMES.get(0));
        taskUpdateDTO.setAnswer(JsonNullable.of("Some answer"));
        taskUpdateDTO.setMark(JsonNullable.of(Score.THREE));
        taskMapper.update(taskUpdateDTO, task);
        taskRepository.save(task);


    }

    public void addDefaultTask11() {
        var taskCreateDTO = new TaskCreateDTO();
        taskCreateDTO.setId(110L);
        taskCreateDTO.setNickname(NICKNAMES.get(1));
        taskCreateDTO.setContent("Important question");
        var task = taskMapper.map(taskCreateDTO);
        var assignee = studentRepository.findByNickname(NICKNAMES.get(1)).orElse(null);
        task.setAssignee(assignee);
        taskRepository.save(task);

        var taskUpdateDTO = new TaskUpdateDTO();
        taskUpdateDTO.setId(110L);
        taskUpdateDTO.setNickname(NICKNAMES.get(1));
        taskUpdateDTO.setAnswer(JsonNullable.of("Some answer"));
        taskUpdateDTO.setMark(JsonNullable.of(Score.FOUR));
        taskMapper.update(taskUpdateDTO, task);
        taskRepository.save(task);
    }

    public void addDefaultTask12() {
        var taskCreateDTO = new TaskCreateDTO();
        taskCreateDTO.setId(120L);
        taskCreateDTO.setNickname(NICKNAMES.get(2));
        taskCreateDTO.setContent("Important question");
        var task = taskMapper.map(taskCreateDTO);
        var assignee = studentRepository.findByNickname(NICKNAMES.get(2)).orElse(null);
        task.setAssignee(assignee);
        taskRepository.save(task);

        var taskUpdateDTO = new TaskUpdateDTO();
        taskUpdateDTO.setId(120L);
        taskUpdateDTO.setNickname(NICKNAMES.get(2));
        taskUpdateDTO.setAnswer(JsonNullable.of("Some answer"));
        taskUpdateDTO.setMark(JsonNullable.of(Score.TWO));
        taskMapper.update(taskUpdateDTO, task);
        taskRepository.save(task);
    }

    public void getStudents() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:9000/api/v1/admin/internal/users";
        String response = restTemplate.getForObject(resourceUrl, String.class);

        List<UserDTO> users = objectMapper.readValue(response, new TypeReference<List<UserDTO>>() {});
        for (var user: users) {
            if (user.getAuthorities().get(0).equals(STUDENT_ROLE)) {
                addStudent(user.getName());
            }
        }
    }
}
