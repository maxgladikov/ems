package org.aston.ems.teacher_service.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task", schema = "app")
public class Task extends BaseEntity {
    private long studentId;
    private long teacherId;
    private String task;
    private boolean checked;
    private int mark;
}
