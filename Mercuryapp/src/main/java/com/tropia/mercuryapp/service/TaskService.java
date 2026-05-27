package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.Client.ReadClientDto;
import com.tropia.mercuryapp.dto.Task.CreateTaskDto;
import com.tropia.mercuryapp.dto.Task.ReadTaskDto;
import com.tropia.mercuryapp.entity.Client;
import com.tropia.mercuryapp.entity.Task;
import com.tropia.mercuryapp.entity.TaskStatus;
import com.tropia.mercuryapp.entity.User;
import com.tropia.mercuryapp.mappers.TaskMapper;
import com.tropia.mercuryapp.repository.TaskJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;
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
    public ReadTaskDto createTask(CreateTaskDto createTaskDto) {
        if (createTaskDto.clientId() == null) {
            throw new RuntimeException("Client ID must not be null");
        }

        User worker = userService.getById(createTaskDto.workerId());
        Client client = clientService.getById(createTaskDto.clientId());
        Instant now = Instant.now();
        Instant end = now.atZone(ZoneId.systemDefault())
                .plus(createTaskDto.durationValue(), createTaskDto.durationUnit())
                .toInstant();
        Task task = Task.builder()
                .client(client)
                .title(createTaskDto.title())
                .description(createTaskDto.description())
                .worker(worker)
                .company(worker.getCompany())
                .createdAt(now)
                .deadline(end)
                .build();
        if (createTaskDto.assigneeId() != null) {
            User manager = userService.getById(createTaskDto.assigneeId());
            task.setAssigned(manager);
            task.setStatus(TaskStatus.TO_DO);
        } else {
            task.setAssigned(null);
            task.setStatus(TaskStatus.IN_PROGRESS);
        }

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
    public List<ReadTaskDto> findByClientId(Long clientId) {
        return taskJpaRepository
                .findByClientId(clientId)
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

    @Transactional(readOnly = true)
    public List<ReadTaskDto> findTasks(Long companyId, Long workerId, Long clientId) {
        List<ReadTaskDto> tasks;
        if (companyId != null) {
            tasks = taskJpaRepository
                    .findByCompanyId(companyId)
                    .stream()
                    .map(taskMapper::entityToDto)
                    .toList();
            return tasks;
        } else if (workerId != null) {
            tasks = taskJpaRepository
                    .findByWorkerId(workerId)
                    .stream()
                    .map(taskMapper::entityToDto)
                    .toList();
            return tasks;
        } else if (clientId != null) {
            tasks = taskJpaRepository
                    .findByClientId(clientId)
                    .stream()
                    .map(taskMapper::entityToDto)
                    .toList();
            return tasks;
        }
        return Collections.emptyList();
    }
}
