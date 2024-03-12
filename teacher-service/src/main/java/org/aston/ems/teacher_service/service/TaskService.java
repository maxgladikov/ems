package org.aston.ems.teacher_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.ems.teacher_service.client.api.IStudentClient;
import org.aston.ems.teacher_service.core.RequestTaskDtoCreate;
import org.aston.ems.teacher_service.core.TaskDtoUpdate;
import org.aston.ems.teacher_service.core.TaskDto;
import org.aston.ems.teacher_service.dao.api.ITaskMapper;
import org.aston.ems.teacher_service.dao.api.ITaskRepository;
import org.aston.ems.teacher_service.dao.api.ITeacherRepository;
import org.aston.ems.teacher_service.dao.model.Task;
import org.aston.ems.teacher_service.dao.model.Teacher;
import org.aston.ems.teacher_service.service.api.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {
    private final ObjectMapper objectMapper;
    private final ITaskRepository taskRepository;
    private final ITeacherRepository teacherRepository;
    private final ITaskMapper taskMapper;
    private final IStudentClient studentClient;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TaskService(ObjectMapper objectMapper, ITaskRepository taskRepository, ITeacherRepository teacherRepository, ITaskMapper taskMapper, IStudentClient studentClient, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.taskRepository = taskRepository;
        this.teacherRepository = teacherRepository;
        this.taskMapper = taskMapper;
        this.studentClient = studentClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void save(TaskDto taskDto) {
        Task entity = taskMapper.toEntity(taskDto);
        Teacher teacher = teacherRepository.findByName(taskDto.getTeacherName());
        entity.setTeacher(teacher);
        Task savedTask = taskRepository.save(entity);

        RequestTaskDtoCreate requestTaskDto = new RequestTaskDtoCreate(savedTask.getId(),
                taskDto.getNickname(), taskDto.getContent());

        studentClient.sendTask(taskDto.getNickname(), requestTaskDto);
    }

    public List<TaskDto> getAllTeachersTasks(String teacherName) {
        Teacher teacher = teacherRepository.findByName(teacherName);
        List<Task> teachersTasks = taskRepository.getAllByTeacher(teacher);
        return toDTOList(teachersTasks);
    }

    public void updateMark(Long id, int mark) throws JsonProcessingException {
        Task task = taskRepository.getReferenceById(id);
        TaskDtoUpdate updateTask = new TaskDtoUpdate(task.getId(),
                task.getNickname(), task.getMark());
        String json = objectMapper.writeValueAsString(updateTask);

        kafkaTemplate.send("mark", json).completable();
        task.setMark(mark);
        task.setChecked(true);
        taskRepository.save(task);
    }

    public void updateAnswer(Long id, String nickName, String answer) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (task.getNickname().equals(nickName)) {
                task.setAnswer(answer);
                taskRepository.save(task);
            }
        }
    }

    private List<TaskDto> toDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

}