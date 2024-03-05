package org.aston.ems.teacher_service.service;

import org.aston.ems.teacher_service.core.TaskDto;

import java.util.List;

public interface ITaskService {
    void save(TaskDto taskDto);
    List<TaskDto> getAllTeachersTasks(long teacherId);
    void delete(long id);

    void update(long id, int mark);
}

