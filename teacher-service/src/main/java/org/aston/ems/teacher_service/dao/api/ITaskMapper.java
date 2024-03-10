package org.aston.ems.teacher_service.dao.api;

import org.aston.ems.teacher_service.core.TaskDto;
import org.aston.ems.teacher_service.dao.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITaskMapper {
    @Mapping(source = "teacherId", target = "teacherId.id")
    Task toEntity(TaskDto dto);

    @Mapping(source = "teacherId.id", target = "teacherId")
    TaskDto toDTO(Task entity);
}