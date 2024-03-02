package org.aston.ems.teacher_service.dao;

import org.aston.ems.teacher_service.core.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITaskMapper {
    Task toEntity(TaskDto dto);

    TaskDto toDTO(Task entity);

    void map(TaskDto dto, @MappingTarget Task entity);

    List<TaskDto> toDTOList(List<Task> entityList);

    List<Task> toEntityList(List<TaskDto> dtoList);
}