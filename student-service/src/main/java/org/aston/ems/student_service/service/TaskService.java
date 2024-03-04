package org.aston.ems.student_service.service;

import lombok.AllArgsConstructor;
import org.aston.ems.student_service.dto.TaskCreateDTO;
import org.aston.ems.student_service.dto.TaskDTO;
import org.aston.ems.student_service.dto.TaskUpdateDTO;
import org.aston.ems.student_service.exception.ResourceNotFoundException;
import org.aston.ems.student_service.mapper.TaskMapper;
import org.aston.ems.student_service.repository.StudentRepository;
import org.aston.ems.student_service.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final StudentRepository studentRepository;

    private final TaskMapper taskMapper;

    public List<TaskDTO> getAll(Long studentId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student with id %s not found",
                        studentId)));
        var tasks = taskRepository.findAllByAssignee(student);
        return tasks.stream()
                .map(taskMapper::map)
                .toList();
    }

    public TaskDTO create(TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);

        var assigneeId = taskData.getAssigneeId();
        var assignee = studentRepository.findById(assigneeId).orElse(null);
        task.setAssignee(assignee);

        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public TaskDTO findById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task with id %s not found", id)));
        return taskMapper.map(task);
    }

    public TaskDTO update(TaskUpdateDTO taskData, Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task with id %s not found", id)));
        taskMapper.update(taskData, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
