package com.tropia.mercuryapp.dto.Task;

import com.tropia.mercuryapp.dto.Client.CreateClientDto;

import java.time.temporal.ChronoUnit;

public record CreateTaskDto(
        String title,
        String description,
        Integer durationValue,
        ChronoUnit durationUnit,
        Long clientId
) {
}
