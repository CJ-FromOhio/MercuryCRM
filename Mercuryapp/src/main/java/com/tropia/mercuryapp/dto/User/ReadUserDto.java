package com.tropia.mercuryapp.dto.User;

import com.tropia.mercuryapp.entity.Role;
import lombok.Builder;

@Builder
public record ReadUserDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
