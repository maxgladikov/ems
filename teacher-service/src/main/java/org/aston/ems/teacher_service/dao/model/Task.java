package org.aston.ems.teacher_service.dao.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task", schema = "app")
public class Task extends BaseEntity {
    private String nickname;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    private String content;
    private String answer;
    private boolean checked;
    private int mark;
}
