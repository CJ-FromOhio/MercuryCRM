package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.SubscriptionPlan.CreateSubscriptionPlanDto;
import com.tropia.mercuryapp.dto.SubscriptionPlan.ReadSubscriptionPlanDto;
import com.tropia.mercuryapp.entity.SubscriptionPlan;
import com.tropia.mercuryapp.mappers.SubscriptionPlanMapper;
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
    private final SubscriptionPlanMapper subscriptionPlanMapper;

    @Transactional
    public ReadSubscriptionPlanDto create(CreateSubscriptionPlanDto dto) {
        SubscriptionPlan entity = subscriptionPlanMapper.createToEntity(dto);
        subscriptionPlanJpaRepository.save(entity);
        return subscriptionPlanMapper.entityToDto(entity);
    }
    @Transactional(readOnly = true)
    public List<ReadSubscriptionPlanDto> findAllActivePlans() {
        return subscriptionPlanMapper.entityToDtoList(subscriptionPlanJpaRepository
                .findSubscriptionPlansByActiveIsTrue());
    }
    @Transactional(readOnly = true)
    public ReadSubscriptionPlanDto findActivePlanById(Long id) {
        return subscriptionPlanMapper.entityToDto(subscriptionPlanJpaRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Subscription plan is not active or null")));
    }
    @Transactional(readOnly = true)
    public SubscriptionPlan getActivePlanById(Long id) {
        return subscriptionPlanJpaRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Subscription plan is not active or null"));
    }
    @Transactional()
    public ReadSubscriptionPlanDto setActive(Long id) {
        SubscriptionPlan entity = subscriptionPlanJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Subscription Plan Not Found"));
        entity.setActive(true);
        return subscriptionPlanMapper.entityToDto(entity);
    }
    @Transactional()
    public ReadSubscriptionPlanDto setFalse(Long id) {
        SubscriptionPlan entity = subscriptionPlanJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Subscription Plan Not Found"));
        entity.setActive(false);
        return subscriptionPlanMapper.entityToDto(entity);
    }

}
