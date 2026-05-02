package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.dto.Subscription.ReadSubscriptionDto;
import com.tropia.mercuryapp.entity.*;
import com.tropia.mercuryapp.mappers.SubscriptionMapper;
import com.tropia.mercuryapp.repository.SubsriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubsriptionJpaRepository subscriptionRepository;
    private final SubscriptionPlanService subscriptionPlanService;
    private final PaymentTransactionService paymentTransactionService;
    private final SubscriptionMapper subscriptionMapper;

    @Transactional
    public Subscription createSubscription(Long plan_id, Company company) {
        SubscriptionPlan sp = subscriptionPlanService
                .getActivePlanById(plan_id);

        Instant start = Instant.now();
        Instant end = start.atZone(ZoneId.systemDefault())
                .plus(sp.getDurationValue(), sp.getDurationUnit())
                .toInstant();

        PaymentTransaction pt = paymentTransactionService.create(sp.getPrice(), sp.getCurrency(), company);
        if (pt.getStatus().equals(PaymentStatus.FAILED)) {
            throw new RuntimeException("Payment failed for company: " + company.getId());
        }
        Subscription subscription = Subscription.builder()
                .plan(sp)
                .company(company)
                .startDate(start)
                .endDate(end)
                .active(true)
                .build();
        subscription.addTransaction(pt);
        return subscriptionRepository.save(subscription);
    }
    @Transactional(readOnly = true)
    public ReadSubscriptionDto findActiveSubscriptionByCompanyId(Long companyId) {
        return subscriptionMapper
                .entityToDto(subscriptionRepository
                        .findSubscriptionByCompanyIdAndActiveTrue(companyId)
                        .orElseThrow(() -> new RuntimeException("Subscription not found or not active")));
    }
}
