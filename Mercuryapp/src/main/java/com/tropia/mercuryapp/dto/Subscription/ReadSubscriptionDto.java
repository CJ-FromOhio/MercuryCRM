package com.tropia.mercuryapp.dto.Subscription;

import com.tropia.mercuryapp.dto.Company.ReadCompanyDto;
import com.tropia.mercuryapp.dto.SubscriptionPlan.ReadSubscriptionPlanDto;
import lombok.Builder;

import java.time.Instant;

@Builder
public record ReadSubscriptionDto(
        Long id,
        String companyName,
        Long companyId,
        ReadSubscriptionPlanDto plan,
        Instant startDate,
        Instant endDate,
        Boolean active
) { }
