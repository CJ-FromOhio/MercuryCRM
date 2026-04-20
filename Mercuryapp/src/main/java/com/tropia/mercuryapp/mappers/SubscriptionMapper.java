package com.tropia.mercuryapp.mappers;

import com.tropia.mercuryapp.dto.Subscription.ReadSubscriptionDto;
import com.tropia.mercuryapp.dto.SubscriptionPlan.ReadSubscriptionPlanDto;
import com.tropia.mercuryapp.entity.Subscription;
import com.tropia.mercuryapp.entity.SubscriptionPlan;
import com.tropia.mercuryapp.repository.SubscriptionPlanJpaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {SubscriptionPlanJpaRepository.class, CompanyMapper.class})
public interface SubscriptionMapper {
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    ReadSubscriptionDto entityToDto(Subscription entity);
}
