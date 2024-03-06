package org.aston.ems.teacher_service.service.api;

import org.aston.ems.teacher_service.core.TaskDto;

import java.util.List;

public interface ITaskService {
    void save(TaskDto taskDto);
    List<TaskDto> getAllTeachersTasks(long teacherId);
    void delete(long id);
    void updateMark(long id, int mark);
    void updateAnswer(long id, long studentId, String answer);
}

