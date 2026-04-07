package com.tropia.mercuryapp.dto.SubscriptionPlan;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateSubscriptionPlanDto(
        String name,
        String description,
        BigDecimal price,
        String currency,
        Integer maxWorkers
) {
}
