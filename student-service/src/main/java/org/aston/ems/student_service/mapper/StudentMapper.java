package org.aston.ems.student_service.mapper;

import lombok.Getter;
import org.aston.ems.student_service.dto.*;
import org.aston.ems.student_service.model.Student;
import org.aston.ems.student_service.repository.TaskRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class StudentMapper {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    public abstract Student map(StudentCreateDTO dto);

    public abstract StudentDTO map(Student model);

    @Mapping(target = "tasks",
            expression = "java(getTaskRepository().findAllByAssignee(model)." +
                    "stream().map(getTaskMapper()::map).toList())")
    public abstract StudentProgressDataDTO mapToProgressData(Student model);

    public abstract void update(StudentUpdateDTO dto, @MappingTarget Student model);
}
