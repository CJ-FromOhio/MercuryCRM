package com.tropia.mercuryapp.controllers;

import com.tropia.mercuryapp.dto.User.CreateUserDto;
import com.tropia.mercuryapp.dto.User.ReadUserDto;
import com.tropia.mercuryapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ReadUserDto>  create(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadUserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}
