package org.aston.ems.teacher_service.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.aston.ems.teacher_service.core.TaskDto;

import java.util.List;

public interface ITaskService {
    void save(TaskDto taskDto);
    List<TaskDto> getAllTeachersTasks(String teachersName);
    void updateMark(Long id, int mark) throws JsonProcessingException;
    void updateAnswer(Long id, String nickName, String answer);
}

