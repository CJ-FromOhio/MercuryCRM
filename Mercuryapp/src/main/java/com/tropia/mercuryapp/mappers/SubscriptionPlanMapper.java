package com.tropia.mercuryapp.mappers;

import com.tropia.mercuryapp.dto.SubscriptionPlan.CreateSubscriptionPlanDto;
import com.tropia.mercuryapp.dto.SubscriptionPlan.ReadSubscriptionPlanDto;
import com.tropia.mercuryapp.dto.User.CreateUserDto;
import com.tropia.mercuryapp.dto.User.ReadUserDto;
import com.tropia.mercuryapp.entity.SubscriptionPlan;
import com.tropia.mercuryapp.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionPlanMapper {
    SubscriptionPlan сreateToEntity(CreateSubscriptionPlanDto dto);
    ReadSubscriptionPlanDto entityToDto(SubscriptionPlan entity);
    List<ReadSubscriptionPlanDto> entityToDtoList(List<SubscriptionPlan> entities);
}
