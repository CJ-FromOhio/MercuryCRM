package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.Task.CreateTaskDto;
import com.tropia.mercuryapp.dto.Task.ReadTaskDto;
import com.tropia.mercuryapp.entity.Client;
import com.tropia.mercuryapp.entity.Task;
import com.tropia.mercuryapp.entity.User;
import com.tropia.mercuryapp.mappers.TaskMapper;
import com.tropia.mercuryapp.repository.TaskJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskJpaRepository taskJpaRepository;
    private final ClientService clientService;
    private final UserService userService;
    private final TaskMapper taskMapper;

    @Transactional
    public ReadTaskDto createTask(CreateTaskDto createTaskDto, Long workerId, Long assigneeId) {
        if (createTaskDto.clientId() == null) {
            throw new RuntimeException("Client ID must not be null");
        }

        User worker = userService.getById(workerId);
        User manager = userService.getById(assigneeId);
        Client client = clientService.getById(createTaskDto.clientId());

        Instant now = Instant.now();
        Instant end = now.atZone(ZoneId.systemDefault())
                .plus(createTaskDto.durationValue(), createTaskDto.durationUnit())
                .toInstant();
        Task task = Task.builder()
                .client(client)
                .title(createTaskDto.title())
                .description(createTaskDto.description())
                .assigned(manager)
                .worker(worker)
                .company(worker.getCompany())
                .createdAt(now)
                .deadline(end)
                .build();
        return taskMapper.entityToDto(taskJpaRepository.save(task));
    }
    @Transactional(readOnly = true)
    public ReadTaskDto findById(Long taskId) {
        return taskMapper.entityToDto(taskJpaRepository
                .findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found by id: " + taskId)));
    }
    @Transactional(readOnly = true)
    public List<ReadTaskDto> findByCompanyId(Long companyId) {
        return taskJpaRepository
                .findByCompanyId(companyId)
                .stream()
                .map(taskMapper::entityToDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public List<ReadTaskDto> findByWorkerId(Long workerId) {
        return taskJpaRepository
                .findByWorkerId(workerId)
                .stream()
                .map(taskMapper::entityToDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public Task getById(Long taskId) {
        return taskJpaRepository
                .findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found by id: " + taskId));
    }
}
