package com.tropia.mercuryapp.dto.Task;

import com.tropia.mercuryapp.dto.Client.ReadClientDto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public record ReadTaskDto(
        Long id,
        String title,
        String description,
        Instant deadline
) {
}
