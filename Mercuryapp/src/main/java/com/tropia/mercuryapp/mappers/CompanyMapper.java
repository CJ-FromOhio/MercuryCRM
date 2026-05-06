package com.tropia.mercuryapp.mappers;

import com.tropia.mercuryapp.dto.Company.CreateCompanyDto;
import com.tropia.mercuryapp.dto.Company.ReadCompanyDto;
import com.tropia.mercuryapp.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CompanyMapper {
    Company createToEntity(CreateCompanyDto dto);
    @Mapping(target = "directorId", source = "director.id")
    ReadCompanyDto entityToDto(Company entity);
    @Mapping(target = "directorId", source = "director.id")
    List<ReadCompanyDto> entityToDtoList(List<Company> entities);
}
