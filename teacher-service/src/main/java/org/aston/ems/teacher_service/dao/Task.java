package org.aston.ems.teacher_service.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "task")
public class Task extends BaseEntity{
    @Column
    private long studentId;
    @Column
    private long teacherId;
    @Column
    private String task;
    @Column
    private boolean checked;
    @Column
    private int mark;
}
