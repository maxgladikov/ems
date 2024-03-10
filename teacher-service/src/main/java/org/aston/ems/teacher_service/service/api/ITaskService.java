package org.aston.ems.teacher_service.service.api;

import org.aston.ems.teacher_service.core.TaskDto;

import java.util.List;

public interface ITaskService {
    void save(TaskDto taskDto);
    List<TaskDto> getAllTeachersTasks(String teachersName);
    void delete(Long id);
    void updateMark(Long id, int mark);
    void updateAnswer(Long id, String nickName, String answer);
}

