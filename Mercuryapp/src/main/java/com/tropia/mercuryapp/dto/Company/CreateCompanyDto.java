package com.tropia.mercuryapp.dto.Company;

import lombok.Builder;

@Builder
public record CreateCompanyDto(
        String name,
        String address
) {
}
