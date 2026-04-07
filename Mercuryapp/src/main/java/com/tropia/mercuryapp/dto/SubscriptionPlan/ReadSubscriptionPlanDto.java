package com.tropia.mercuryapp.dto.SubscriptionPlan;

import jakarta.persistence.Basic;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ReadSubscriptionPlanDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String currency,
        Integer maxWorkers,
        Boolean active
) {
}
