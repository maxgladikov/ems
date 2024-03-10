package org.aston.ems.teacher_service.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aston.ems.teacher_service.dao.util.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher", schema = "app")
public class Teacher extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Role authorities;
    @OneToMany(mappedBy = "teacherId", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Task> tasks;
}
