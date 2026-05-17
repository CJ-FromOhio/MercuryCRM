package com.tropia.mercuryapp.dto.Client;

public record ReadClientDto(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {
}
