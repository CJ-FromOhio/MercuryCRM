package com.tropia.mercuryapp.dto.SubscriptionPlan;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Builder
public record CreateSubscriptionPlanDto(
        String name,
        String description,
        BigDecimal price,
        String currency,
        Integer durationValue,
        ChronoUnit durationUnit,
        Integer maxWorkers
) {
}
