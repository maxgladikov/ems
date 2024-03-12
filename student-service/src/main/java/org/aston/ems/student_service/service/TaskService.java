package org.aston.ems.student_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.aston.ems.student_service.dto.TaskCreateDTO;
import org.aston.ems.student_service.dto.TaskDTO;
import org.aston.ems.student_service.dto.TaskUpdateDTO;
import org.aston.ems.student_service.exception.ResourceNotFoundException;
import org.aston.ems.student_service.mapper.TaskMapper;
import org.aston.ems.student_service.repository.StudentRepository;
import org.aston.ems.student_service.repository.TaskRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class TaskService {
    private final KafkaTemplate kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final TaskRepository taskRepository;

    private final StudentRepository studentRepository;

    private final TaskMapper taskMapper;

    public List<TaskDTO> getAll(String nickname) {
        var student = studentRepository.findByNickname(nickname)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student with nickname %s not found",
                        nickname)));
        var tasks = taskRepository.findAllByAssignee(student);
        return tasks.stream()
                .map(taskMapper::map)
                .toList();
    }

    public TaskDTO create(TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);

        var nickname = taskData.getNickname();
        var assignee = studentRepository.findByNickname(nickname).orElse(null);
        task.setAssignee(assignee);

        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public TaskDTO findById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task with id %s not found", id)));
        return taskMapper.map(task);
    }

    @KafkaListener(topics = "mark", containerFactory = "kafkaListenerContainerFactory")
    public TaskDTO update(TaskUpdateDTO taskData) {
        var taskId = taskData.getId();
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task with id %s not found", taskId)));
        taskMapper.update(taskData, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
