package org.aston.ems.teacher_service.service;

import org.aston.ems.teacher_service.core.TeacherDtoResp;
import org.aston.ems.teacher_service.dao.api.ITeacherMapper;
import org.aston.ems.teacher_service.dao.api.ITeacherRepository;
import org.aston.ems.teacher_service.dao.model.Teacher;
import org.aston.ems.teacher_service.dao.util.Role;
import org.aston.ems.teacher_service.service.api.ITeacherService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;

@Service
public class TeacherService implements ITeacherService {
    private final ITeacherRepository repository;
    private final RestTemplate restTemplate;
    private static final String RESOURCE_URL = "http://host.docker.internal:9000/api/v1/admin/users";


    public TeacherService(ITeacherRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }


    @Override
    public void saveTeachers() {
        ResponseEntity<TeacherDtoResp[]> responseEntity = restTemplate.getForEntity(RESOURCE_URL, TeacherDtoResp[].class, headers);
        TeacherDtoResp[] dtoResps = responseEntity.getBody();

        for (TeacherDtoResp dtoResp : dtoResps) {
            if (Arrays.asList(dtoResp.getAuthorities()).contains(Role.TEACHER.name())) {
                Teacher teacher = new Teacher();
                teacher.setName(dtoResp.getName());
                teacher.setAuthorities(Role.TEACHER);
                repository.save(teacher);
            }
        }
    }
}
