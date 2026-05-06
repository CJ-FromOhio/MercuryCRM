package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.Company.CreateCompanyDto;
import com.tropia.mercuryapp.dto.Company.ReadCompanyDto;
import com.tropia.mercuryapp.entity.Company;
import com.tropia.mercuryapp.entity.User;
import com.tropia.mercuryapp.mappers.CompanyMapper;
import com.tropia.mercuryapp.repository.CompanyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyJpaRepository companyRepository;
    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final CompanyMapper companyMapper;


    @Transactional
    public ReadCompanyDto create(CreateCompanyDto dto, Long directorId) {
        User director = userService.getById(directorId);
        Company company = companyMapper.createToEntity(dto);
        company.setCreatedAt(Instant.now());
        if(director.getCompany()!=null){
            throw new RuntimeException("Sorry but you can create only one company");
        }
        company.setDirector(director);
        Company savedCompany = companyRepository.save(company);
        subscriptionService.createSubscription(dto.plan_id(), savedCompany);
        return companyMapper.entityToDto(savedCompany);
    }
    @Transactional(readOnly = true)
    public ReadCompanyDto findById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }
}
