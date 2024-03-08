package org.aston.ems.student_service.mapper;

import org.aston.ems.student_service.dto.StudentCreateDTO;
import org.aston.ems.student_service.dto.StudentDTO;
import org.aston.ems.student_service.dto.StudentUpdateDTO;
import org.aston.ems.student_service.model.Student;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class StudentMapper {

    public abstract Student map(StudentCreateDTO dto);

    public abstract StudentDTO map(Student model);

    public abstract void update(StudentUpdateDTO dto, @MappingTarget Student model);
}
