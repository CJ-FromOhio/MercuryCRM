package com.tropia.mercuryapp.dto.User;

import lombok.Builder;

@Builder
public record CreateUserDto(
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String passwordConfirmation
){}
