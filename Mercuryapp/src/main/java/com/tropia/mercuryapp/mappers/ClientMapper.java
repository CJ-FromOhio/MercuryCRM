package com.tropia.mercuryapp.mappers;

import com.tropia.mercuryapp.dto.Client.CreateClientDto;
import com.tropia.mercuryapp.dto.Client.ReadClientDto;
import com.tropia.mercuryapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class,CompanyMapper.class})
public interface ClientMapper {
    Client createToEntity(CreateClientDto dto);
    @Mapping(target = "companyId", source = "company.id")
    ReadClientDto entityToDto(Client enity);
}
