package org.aston.ems.teacher_service.service;

import org.aston.ems.teacher_service.client.api.IStudentClient;
import org.aston.ems.teacher_service.core.RequestTaskDtoCreate;
import org.aston.ems.teacher_service.core.TaskDtoUpdate;
import org.aston.ems.teacher_service.core.TaskDto;
import org.aston.ems.teacher_service.dao.api.ITaskMapper;
import org.aston.ems.teacher_service.dao.api.ITaskRepository;
import org.aston.ems.teacher_service.dao.model.Task;
import org.aston.ems.teacher_service.service.api.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {
    private final ITaskRepository repository;
    private final ITaskMapper mapper;
    private final IStudentClient studentClient;

    @Autowired
    public TaskService(ITaskRepository repository, ITaskMapper mapper, IStudentClient studentClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.studentClient = studentClient;
    }

    public void save(TaskDto taskDto) {
        Task savedTask = repository.save(mapper.toEntity(taskDto));

        RequestTaskDtoCreate requestTaskDto = new RequestTaskDtoCreate(savedTask.getId(),
                taskDto.getStudentId(), taskDto.getContent());

        studentClient.sendTask(taskDto.getStudentId(), requestTaskDto);
    }

    public List<TaskDto> getAllTeachersTasks(long teacherId) {
        List<Task> teachersTasks = repository.getAllByTeacherId(teacherId);
        return toDTOList(teachersTasks);
    }

    public void delete(long id) {
        Task task = repository.getReferenceById(id);
        repository.delete(task);
    }

    public void updateMark(long id, int mark) {
        Task task = repository.getReferenceById(id);
        task.setMark(mark);
        task.setChecked(true);
        repository.save(task);

        TaskDtoUpdate updateTask = new TaskDtoUpdate(task.getId(),
                task.getStudentId(), task.getMark());
        studentClient.sendMark(task.getStudentId(), task.getId(), updateTask);
    }

    @Override
    public void updateAnswer(long id, long studentId, String answer) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (task.getStudentId() == studentId) {
                task.setAnswer(answer);
                repository.save(task);
            }
        }
    }

    private List<TaskDto> toDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}