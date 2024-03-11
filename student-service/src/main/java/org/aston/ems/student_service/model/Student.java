package org.aston.ems.student_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "students")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Student implements BaseEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = IDENTITY, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "seq_student_id", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String nickname;

    @CreatedDate
    private LocalDate createdAt;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();
}
