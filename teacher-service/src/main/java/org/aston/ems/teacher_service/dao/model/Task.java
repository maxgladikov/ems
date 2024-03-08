package org.aston.ems.teacher_service.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.aston.ems.teacher_service.dao.model.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task", schema = "app")
public class Task extends BaseEntity {
    private Long studentId;
    private Long teacherId;
    private String content;
    private String answer;
    private boolean checked;
    private int mark;
}
