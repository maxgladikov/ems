package org.aston.ems.teacher_service.client;

import org.aston.ems.teacher_service.client.api.IStudentClient;
import org.aston.ems.teacher_service.core.RequestTaskDtoCreate;
import org.aston.ems.teacher_service.core.TaskDtoUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StudentClient implements IStudentClient {
    private final RestTemplate restTemplate;
    private static final String SERVICE_URL_TASK = "http://host.docker.internal:9001/api/v1/students/%s/tasks";
    private static final String SERVICE_URL_MARK = "http://host.docker.internal:9001/api/v1/students/%d/tasks/%d";


    @Autowired
    public StudentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendTask(long studentId, RequestTaskDtoCreate task) {
        String url = String.format(SERVICE_URL_TASK, studentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestTaskDtoCreate> request = new HttpEntity<>(task, headers);
        restTemplate.postForObject(url, request, Void.class);
    }

    @Override
    public void sendMark(Long studentId, Long taskId, TaskDtoUpdate request) {
        String url = String.format(SERVICE_URL_MARK, studentId, taskId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TaskDtoUpdate> requestEntity = new HttpEntity<>(request, headers);
        restTemplate.put(url, requestEntity);
    }
}
