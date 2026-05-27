package com.tropia.mercuryapp.service;

import com.querydsl.core.BooleanBuilder;
import com.tropia.mercuryapp.dto.Client.ReadClientDto;
import com.tropia.mercuryapp.dto.Task.CreateTaskDto;
import com.tropia.mercuryapp.dto.Task.ReadTaskDto;
import com.tropia.mercuryapp.dto.User.ReadUserDto;
import com.tropia.mercuryapp.entity.*;
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
import java.util.stream.StreamSupport;

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
        QTask qTask = QTask.task;
        BooleanBuilder predicate = new BooleanBuilder();
        if(companyId != null) {
            predicate.and(qTask.company.id.eq(companyId));
        }
        if(workerId != null) {
            predicate.and(qTask.worker.id.eq(workerId));
        }
        if(clientId != null) {
            predicate.and(qTask.client.id.eq(clientId));
        }
        Iterable<Task> tasks =  taskJpaRepository.findAll(predicate);
        return StreamSupport.stream(tasks.spliterator(), false)
                .map(taskMapper::entityToDto)
                .toList();
    }
}
