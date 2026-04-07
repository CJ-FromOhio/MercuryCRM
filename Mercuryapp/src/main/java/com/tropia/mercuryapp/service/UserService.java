package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.User.CreateUserDto;
import com.tropia.mercuryapp.dto.User.ReadUserDto;
import com.tropia.mercuryapp.entity.Role;
import com.tropia.mercuryapp.entity.User;
import com.tropia.mercuryapp.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    @Transactional
    public ReadUserDto createUser(CreateUserDto dto){
        if(!dto.password().equals(dto.passwordConfirmation())){
            throw new IllegalArgumentException("Passwords not equals");
        }
        User user = User.builder()
                .username(dto.username())
                .password(dto.password())
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .createdAt(Instant.now())
                .role(Role.ROLE_OWNER)
                .company(null)
                .build();
        userJpaRepository.save(user);
        return ReadUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
