package org.aston.ems.teacher_service.dao.api;

import org.aston.ems.teacher_service.core.TeacherDto;
import org.aston.ems.teacher_service.dao.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITeacherMapper {
    Teacher toEntity(TeacherDto dto);

    TeacherDto toDTO(Teacher entity);
}
