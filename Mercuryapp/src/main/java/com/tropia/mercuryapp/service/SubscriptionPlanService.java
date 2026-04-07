package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.SubscriptionPlan.CreateSubscriptionPlanDto;
import com.tropia.mercuryapp.dto.SubscriptionPlan.ReadSubscriptionPlanDto;
import com.tropia.mercuryapp.entity.SubscriptionPlan;
import com.tropia.mercuryapp.repository.SubscriptionPlanJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionPlanService {
    private final SubscriptionPlanJpaRepository subscriptionPlanJpaRepository;
    @Transactional
    public ReadSubscriptionPlanDto create(CreateSubscriptionPlanDto dto) {
        SubscriptionPlan entity = mapCreateToEntity(dto);
        subscriptionPlanJpaRepository.save(entity);
        return mapEntityToRead(entity);
    }
    @Transactional(readOnly = true)
    public List<ReadSubscriptionPlanDto> findAllActivePlans() {
        return subscriptionPlanJpaRepository.findSubscriptionPlansByActiveIsTrue()
                .stream()
                .map(this::mapEntityToRead)
                .toList();
    }
    @Transactional()
    public ReadSubscriptionPlanDto setActive(Long id) {
        SubscriptionPlan entity = subscriptionPlanJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Subscription Plan Not Found"));
        entity.setActive(true);
        return mapEntityToRead(entity);
    }
    @Transactional()
    public ReadSubscriptionPlanDto setFalse(Long id) {
        SubscriptionPlan entity = subscriptionPlanJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Subscription Plan Not Found"));
        entity.setActive(false);
        return mapEntityToRead(entity);
    }

    private ReadSubscriptionPlanDto mapEntityToRead(SubscriptionPlan entity) {
        return ReadSubscriptionPlanDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .maxWorkers(entity.getMaxWorkers())
                .active(entity.getActive())
                .build();
    }
    private SubscriptionPlan mapCreateToEntity(CreateSubscriptionPlanDto dto) {
        return SubscriptionPlan.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .currency(dto.currency())
                .maxWorkers(dto.maxWorkers())
                .active(false)
                .build();
    }
}
