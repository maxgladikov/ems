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
        Task entity = mapper.toEntity(taskDto);
        Task savedTask = repository.save(entity);

        RequestTaskDtoCreate requestTaskDto = new RequestTaskDtoCreate(savedTask.getId(),
                taskDto.getNickName(), taskDto.getContent());

        studentClient.sendTask(taskDto.getNickName(), requestTaskDto);
    }

    public List<TaskDto> getAllTeachersTasks(Long teacherId) {
        List<Task> teachersTasks = repository.getAllByTeacherId(teacherId);
        return toDTOList(teachersTasks);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void updateMark(Long id, int mark) {
        Task task = repository.getReferenceById(id);
        task.setMark(mark);
        task.setChecked(true);
        repository.save(task);

        TaskDtoUpdate updateTask = new TaskDtoUpdate(task.getId(),
                task.getNickName(), task.getMark());
        studentClient.sendMark(task.getId(), updateTask);
    }

    public void updateAnswer(Long id, String nickName, String answer) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (task.getNickName().equals(nickName)) {
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