package org.aston.ems.teacher_service.client.api;

import org.aston.ems.teacher_service.core.RequestTaskDtoCreate;
import org.aston.ems.teacher_service.core.TaskDtoUpdate;

public interface IStudentClient {
    void sendTask(String nickName, RequestTaskDtoCreate task);
    void sendMark(Long taskId, TaskDtoUpdate request);
}
