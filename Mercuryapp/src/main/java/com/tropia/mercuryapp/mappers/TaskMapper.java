package com.tropia.mercuryapp.mappers;

import com.tropia.mercuryapp.dto.Task.CreateTaskDto;
import com.tropia.mercuryapp.dto.Task.ReadTaskDto;
import com.tropia.mercuryapp.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface TaskMapper {
    Task createToEntity(CreateTaskDto dto);
    ReadTaskDto entityToDto(Task enity);
}
