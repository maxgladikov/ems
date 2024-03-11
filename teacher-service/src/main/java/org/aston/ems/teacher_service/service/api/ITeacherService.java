package org.aston.ems.teacher_service.service.api;

import org.aston.ems.teacher_service.core.TeacherDto;

public interface ITeacherService {
//    void saveTeachers();
    void createTeacher(TeacherDto teacherDto);
    void getByName(String name);
}
