package com.tropia.mercuryapp.mappers;

import com.tropia.mercuryapp.dto.User.CreateUserDto;
import com.tropia.mercuryapp.dto.User.CreateUserWorkerDto;
import com.tropia.mercuryapp.dto.User.ReadUserDto;
import com.tropia.mercuryapp.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createToEntity(CreateUserDto dto);
    User createWorkerToEntity(CreateUserWorkerDto dto);
    ReadUserDto entityToDto(User enity);
    List<ReadUserDto> entityToDtoList(List<User> entities);
}
