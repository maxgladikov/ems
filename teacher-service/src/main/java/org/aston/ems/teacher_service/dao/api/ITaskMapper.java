package org.aston.ems.teacher_service.dao.api;

import org.aston.ems.teacher_service.core.TaskDto;
import org.aston.ems.teacher_service.dao.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ITaskMapper {
    Task toEntity(TaskDto dto);

    TaskDto toDTO(Task entity);

    void map(TaskDto dto, @MappingTarget Task entity);
}