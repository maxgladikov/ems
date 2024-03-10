package org.aston.ems.teacher_service.service;

import org.aston.ems.teacher_service.core.TeacherDto;
import org.aston.ems.teacher_service.dao.api.ITeacherMapper;
import org.aston.ems.teacher_service.dao.api.ITeacherRepository;
import org.aston.ems.teacher_service.service.api.ITeacherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeacherService implements ITeacherService {
    private final ITeacherRepository repository;
    private final ITeacherMapper teacherMapper;
    private final RestTemplate restTemplate;
    private static final String RESOURCE_URL = "http://host.docker.internal:9000/api/v1/admin/users";


    public TeacherService(ITeacherRepository repository, ITeacherMapper teacherMapper, RestTemplate restTemplate) {
        this.repository = repository;
        this.teacherMapper = teacherMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public void createTeacher(TeacherDto teacherDto) {
        repository.save(teacherMapper.toEntity(teacherDto));
    }

//
//    @Override
//    public void saveTeachers() {
//        ResponseEntity<TeacherDtoResp[]> responseEntity = restTemplate.getForEntity(RESOURCE_URL, TeacherDtoResp[].class, headers);
//        TeacherDtoResp[] dtoResps = responseEntity.getBody();
//
//        for (TeacherDtoResp dtoResp : dtoResps) {
//            if (Arrays.asList(dtoResp.getAuthorities()).contains(Role.TEACHER.name())) {
//                Teacher teacher = new Teacher();
//                teacher.setName(dtoResp.getName());
//                teacher.setAuthorities(Role.TEACHER);
//                repository.save(teacher);
//            }
//        }
//    }
}
