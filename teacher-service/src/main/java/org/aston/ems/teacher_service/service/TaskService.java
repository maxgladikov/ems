package org.aston.ems.teacher_service.service;

import org.aston.ems.teacher_service.core.TaskDto;
import org.aston.ems.teacher_service.dao.ITaskMapper;
import org.aston.ems.teacher_service.dao.ITaskRepository;
import org.aston.ems.teacher_service.dao.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService{
    private static final String ILLEGAL_ARGUMENT_ERR_MSG = "Mark should be between 0 and 10";
    private final ITaskRepository repository;
    private final ITaskMapper mapper;

    @Autowired
    public TaskService(ITaskRepository repository, ITaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void save(TaskDto taskDto) {
        repository.save(mapper.toEntity(taskDto));
    }

    @Override
    public List<TaskDto> getAllTeachersTasks(long teacherId) {
        List<Task> teachersTasks = repository.getAllById(teacherId);
        return mapper.toDTOList(teachersTasks);
    }

    @Override
    public void delete(long id) {
        Task task = repository.getReferenceById(id);
        repository.delete(task);
    }

    @Override
    public void update(long id, int mark) {
        if (mark >= 0 && mark <= 5) {
            Task task = repository.getReferenceById(id);
            task.setMark(mark);
            task.setChecked(true);
            repository.save(task);
        } else {
        throw new IllegalArgumentException(ILLEGAL_ARGUMENT_ERR_MSG);
        }
    }
}
