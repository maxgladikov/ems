package org.aston.ems.student_service.component;

import lombok.AllArgsConstructor;
import org.aston.ems.student_service.dto.StudentCreateDTO;
import org.aston.ems.student_service.dto.TaskCreateDTO;
import org.aston.ems.student_service.dto.TaskUpdateDTO;
import org.aston.ems.student_service.mapper.StudentMapper;
import org.aston.ems.student_service.mapper.TaskMapper;
import org.aston.ems.student_service.model.Score;
import org.aston.ems.student_service.repository.StudentRepository;
import org.aston.ems.student_service.repository.TaskRepository;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private static final List<String> NICKNAMES = List.of("Ivan Ivanov", "Fedor Fedorov", "Oleg Olegov");

    private final StudentRepository studentRepository;

    private final TaskRepository taskRepository;

    private final StudentMapper studentMapper;

    private final TaskMapper taskMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addDefaultStudents();
        addDefaultTask10();
        addDefaultTask11();
        addDefaultTask12();
    }

    public void addDefaultStudents() {
        for (var nickname: NICKNAMES) {
            var studentCreateDTO = new StudentCreateDTO();
            studentCreateDTO.setNickname(nickname);
            var student = studentMapper.map(studentCreateDTO);
            studentRepository.save(student);
        }
    }

    public void addDefaultTask10() {
        var taskCreateDTO = new TaskCreateDTO();
        taskCreateDTO.setId(10L);
        taskCreateDTO.setNickname(NICKNAMES.get(0));
        taskCreateDTO.setContent("Important question");
        var task = taskMapper.map(taskCreateDTO);
        var assignee = studentRepository.findByNickname(NICKNAMES.get(0)).orElse(null);
        task.setAssignee(assignee);
        taskRepository.save(task);

        var taskUpdateDTO = new TaskUpdateDTO();
        taskUpdateDTO.setId(10L);
        taskUpdateDTO.setNickname(NICKNAMES.get(0));
        taskUpdateDTO.setAnswer(JsonNullable.of("Some answer"));
        taskUpdateDTO.setMark(JsonNullable.of(Score.THREE));
        taskMapper.update(taskUpdateDTO, task);
        taskRepository.save(task);


    }

    public void addDefaultTask11() {
        var taskCreateDTO = new TaskCreateDTO();
        taskCreateDTO.setId(11L);
        taskCreateDTO.setNickname(NICKNAMES.get(1));
        taskCreateDTO.setContent("Important question");
        var task = taskMapper.map(taskCreateDTO);
        var assignee = studentRepository.findByNickname(NICKNAMES.get(1)).orElse(null);
        task.setAssignee(assignee);
        taskRepository.save(task);

        var taskUpdateDTO = new TaskUpdateDTO();
        taskUpdateDTO.setId(11L);
        taskUpdateDTO.setNickname(NICKNAMES.get(1));
        taskUpdateDTO.setAnswer(JsonNullable.of("Some answer"));
        taskUpdateDTO.setMark(JsonNullable.of(Score.FOUR));
        taskMapper.update(taskUpdateDTO, task);
        taskRepository.save(task);
    }

    public void addDefaultTask12() {
        var taskCreateDTO = new TaskCreateDTO();
        taskCreateDTO.setId(12L);
        taskCreateDTO.setNickname(NICKNAMES.get(2));
        taskCreateDTO.setContent("Important question");
        var task = taskMapper.map(taskCreateDTO);
        var assignee = studentRepository.findByNickname(NICKNAMES.get(2)).orElse(null);
        task.setAssignee(assignee);
        taskRepository.save(task);

        var taskUpdateDTO = new TaskUpdateDTO();
        taskUpdateDTO.setId(12L);
        taskUpdateDTO.setNickname(NICKNAMES.get(2));
        taskUpdateDTO.setAnswer(JsonNullable.of("Some answer"));
        taskUpdateDTO.setMark(JsonNullable.of(Score.TWO));
        taskMapper.update(taskUpdateDTO, task);
        taskRepository.save(task);
    }
}
