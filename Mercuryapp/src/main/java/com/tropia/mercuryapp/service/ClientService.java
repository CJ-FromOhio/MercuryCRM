package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.Client.CreateClientDto;
import com.tropia.mercuryapp.dto.Client.ReadClientDto;
import com.tropia.mercuryapp.entity.Client;
import com.tropia.mercuryapp.entity.Company;
import com.tropia.mercuryapp.mappers.ClientMapper;
import com.tropia.mercuryapp.repository.ClientJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientJpaRepository clientJpaRepository;
    private final ClientMapper clientMapper;
    private final CompanyService companyService;

    @Transactional()
    public ReadClientDto create(CreateClientDto createClientDto, Long companyId) {
        Client client = clientMapper.createToEntity(createClientDto);
        Company company = companyService.getById(companyId);
        company.addClient(client);
        Client savedClient = clientJpaRepository.save(client);
        return clientMapper.entityToDto(savedClient);
    }
    @Transactional(readOnly = true)
    public ReadClientDto findById(Long clientId) {
        return clientMapper.entityToDto(clientJpaRepository
                .findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found by id: " + clientId)));
    }
    @Transactional(readOnly = true)
    public ReadClientDto findByEmail(String email) {
        return clientMapper.entityToDto(clientJpaRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Client not found by email: " + email)));
    }
    @Transactional(readOnly = true)
    public ReadClientDto findByPhoneNumber(String phoneNumber) {
        return clientMapper.entityToDto(clientJpaRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Client not found by phoneNumber: " + phoneNumber)));
    }
    @Transactional(readOnly = true)
    public Client getById(Long clientId) {
        return clientJpaRepository
                .findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found by id: " + clientId));
    }
    @Transactional(readOnly = true)
    public List<ReadClientDto> getByCompanyId(Long companyId) {
        return clientJpaRepository
                .findByCompanyId(companyId)
                .stream().map(clientMapper::entityToDto).toList();
    }
}
