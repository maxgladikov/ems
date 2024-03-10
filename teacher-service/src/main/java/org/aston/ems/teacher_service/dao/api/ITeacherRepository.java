package org.aston.ems.teacher_service.dao.api;

import org.aston.ems.teacher_service.dao.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByName(String name);
}
