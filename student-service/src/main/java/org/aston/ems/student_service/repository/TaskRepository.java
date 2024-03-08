package org.aston.ems.student_service.repository;

import org.aston.ems.student_service.model.Student;
import org.aston.ems.student_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByAssignee(Student assignee);
}
