package com.tropia.mercuryapp.dto.User;

public record CreateUserWorkerDto(
        String username,
        String firstName,
        String lastName,
        String email,
        String password,
        String passwordConfirmation
) {
}
