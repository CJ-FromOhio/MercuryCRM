package com.tropia.mercuryapp.mappers;

import com.tropia.mercuryapp.dto.Client.CreateClientDto;
import com.tropia.mercuryapp.dto.Client.ReadClientDto;
import com.tropia.mercuryapp.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ClientMapper {
    Client createToEntity(CreateClientDto dto);
    ReadClientDto entityToDto(Client enity);
}
