package com.tropia.mercuryapp.controllers;

import com.tropia.mercuryapp.dto.SubscriptionPlan.CreateSubscriptionPlanDto;
import com.tropia.mercuryapp.dto.SubscriptionPlan.ReadSubscriptionPlanDto;
import com.tropia.mercuryapp.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plans")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @PostMapping()
    public ResponseEntity<ReadSubscriptionPlanDto> createUser(@RequestBody CreateSubscriptionPlanDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionPlanService.create(dto));
    }
    @GetMapping()
    public ResponseEntity<List<ReadSubscriptionPlanDto>> getAllSubscriptionPlans() {
        return ResponseEntity.ok(subscriptionPlanService.findAllActivePlans());
    }
    @PatchMapping("/{id}/activate")
    public ResponseEntity<ReadSubscriptionPlanDto> activateSubscriptionPlan(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionPlanService.setActive(id));
    }
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ReadSubscriptionPlanDto> deactivateSubscriptionPlan(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionPlanService.setFalse(id));
    }
}
