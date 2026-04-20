package com.tropia.mercuryapp.dto.SubscriptionPlan;

import jakarta.persistence.Basic;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Builder
public record ReadSubscriptionPlanDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String currency,
        Integer maxWorkers,
        Integer durationValue,
        ChronoUnit durationUnit,
        Boolean active
) {
}
