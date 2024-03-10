package org.aston.ems.teacher_service.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task", schema = "app")
public class Task extends BaseEntity {
    private String nickName;
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacherId;
    private String content;
    private String answer;
    private boolean checked;
    private int mark;
}
