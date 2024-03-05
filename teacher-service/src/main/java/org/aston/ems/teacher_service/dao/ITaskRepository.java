package org.aston.ems.teacher_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITaskRepository extends JpaRepository<Task, Long> {
    List<Task> getAllByTeacherId(long teacherId);
}