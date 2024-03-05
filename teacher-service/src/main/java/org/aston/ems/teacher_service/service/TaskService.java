package org.aston.ems.teacher_service.service;

import org.aston.ems.teacher_service.core.TaskDto;
import org.aston.ems.teacher_service.dao.ITaskMapper;
import org.aston.ems.teacher_service.dao.ITaskRepository;
import org.aston.ems.teacher_service.dao.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {
    private static final String ILLEGAL_ARGUMENT_ERR_MSG = "Mark should be between 0 and 5";
    private final ITaskRepository repository;
    private final ITaskMapper mapper;

    @Autowired
    public TaskService(ITaskRepository repository, ITaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(TaskDto taskDto) {
        repository.save(mapper.toEntity(taskDto));
    }

    public List<TaskDto> getAllTeachersTasks(long teacherId) {
        List<Task> teachersTasks = repository.getAllByTeacherId(teacherId);
        return toDTOList(teachersTasks);
    }

    public void delete(long id) {
        Task task = repository.getReferenceById(id);
        repository.delete(task);
    }

    public void update(long id, int mark) {
        Task task = repository.getReferenceById(id);
        task.setMark(mark);
        task.setChecked(true);
        repository.save(task);
    }

    private List<TaskDto> toDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}

