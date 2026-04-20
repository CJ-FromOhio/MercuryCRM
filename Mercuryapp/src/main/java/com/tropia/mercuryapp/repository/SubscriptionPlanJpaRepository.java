package com.tropia.mercuryapp.repository;

import com.tropia.mercuryapp.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionPlanJpaRepository extends JpaRepository<SubscriptionPlan, Long> {
    List<SubscriptionPlan> findSubscriptionPlansByActiveIsTrue();
    Optional<SubscriptionPlan> findByIdAndActiveTrue(Long id);
}
