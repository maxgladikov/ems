package org.aston.ems.student_service.mapper;

import org.aston.ems.student_service.dto.TaskCreateDTO;
import org.aston.ems.student_service.dto.TaskDTO;
import org.aston.ems.student_service.dto.TaskUpdateDTO;
import org.aston.ems.student_service.model.Score;
import org.aston.ems.student_service.model.Task;
import org.mapstruct.*;

@Mapper(
        uses = { JsonNullableMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {

    @Mapping(target = "assignee.nickname", source = "nickname")
    public abstract Task map(TaskCreateDTO dto);

    @Mapping(source = "assignee.nickname", target = "nickname")
    @Mapping(source = "score", target = "mark")
    public abstract TaskDTO map(Task model);

    @Mapping(target = "assignee.nickname", source = "nickname")
    @Mapping(target = "score", source = "mark")
    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task model);

    public int toInt(Score value) {
        if (value != null) {
            return switch (value) {
                case ZERO -> 0;
                case ONE -> 1;
                case TWO -> 2;
                case THREE -> 3;
                case FOUR -> 4;
                case FIVE -> 5;
            };
        } else {
            return 0;
        }
    }
}
