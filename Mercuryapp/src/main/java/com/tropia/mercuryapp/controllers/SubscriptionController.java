package com.tropia.mercuryapp.controllers;

import com.tropia.mercuryapp.dto.Subscription.ReadSubscriptionDto;
import com.tropia.mercuryapp.mappers.SubscriptionMapper;
import com.tropia.mercuryapp.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/{company_id}")
    public ResponseEntity<ReadSubscriptionDto> findSubscriptionByCompany(@PathVariable Long company_id){
        return ResponseEntity.ok(subscriptionService.findActiveSubscriptionByCompanyId(company_id));
    }
}
