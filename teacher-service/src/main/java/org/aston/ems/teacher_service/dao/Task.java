package org.aston.ems.teacher_service.dao;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {
    private long studentId;
    private long teacherId;
    private String task;
    private boolean checked;
    private int mark;
}
