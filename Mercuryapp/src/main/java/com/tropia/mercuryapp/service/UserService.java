package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.User.CreateUserDto;
import com.tropia.mercuryapp.dto.User.ReadUserDto;
import com.tropia.mercuryapp.entity.Role;
import com.tropia.mercuryapp.entity.User;
import com.tropia.mercuryapp.mappers.UserMapper;
import com.tropia.mercuryapp.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Transactional
    public ReadUserDto createUser(CreateUserDto dto){
        if(!dto.password().equals(dto.passwordConfirmation())){
            throw new IllegalArgumentException("Passwords not equals");
        }
        User user = userMapper.сreateToEntity(dto);
        user.setRole(Role.ROLE_OWNER);
        user.setCompany(null);
        user.setCreatedAt(Instant.now());
        userJpaRepository.save(user);
        return userMapper.entityToDto(user);
    }
}
