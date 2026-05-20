package com.tropia.mercuryapp.controllers;

import com.tropia.mercuryapp.dto.Task.CreateTaskDto;
import com.tropia.mercuryapp.dto.Task.ReadTaskDto;
import com.tropia.mercuryapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping()
    public ResponseEntity<ReadTaskDto> create(
            @RequestBody CreateTaskDto dto) {
        return ResponseEntity.ok(taskService.createTask(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadTaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReadTaskDto>> getTasks(
            @RequestParam(value = "companyId", required = false) Long companyId,
            @RequestParam(value = "workerId", required = false) Long workerId,
            @RequestParam(value = "clientId", required = false) Long clientId
    ) {
        // Просто передаем всё в сервис
        return ResponseEntity.ok(taskService.findTasks(companyId, workerId, clientId));
    }
}
