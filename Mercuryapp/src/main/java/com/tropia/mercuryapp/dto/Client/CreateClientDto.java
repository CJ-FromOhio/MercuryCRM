package com.tropia.mercuryapp.dto.Client;

public record CreateClientDto(
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {
}
