package com.tropia.mercuryapp.dto.User;

import com.tropia.mercuryapp.entity.Role;
import lombok.Builder;

@Builder
public record ReadUserDto(
        Long id,
        String username,
        String firstname,
        String lastname,
        String email,
        Role role
) {
}
