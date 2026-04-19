package com.tropia.mercuryapp.dto.Company;

import com.tropia.mercuryapp.dto.User.ReadUserDto;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ReadCompanyDto(
        Long id,
        String name,
        String address,
        Long directorId,
        List<ReadUserDto> workers,
        Instant createdAt
) {
}
