package com.tropia.mercuryapp.repository;

import com.tropia.mercuryapp.entity.Client;
import com.tropia.mercuryapp.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubsriptionJpaRepository extends JpaRepository<Subscription, Long>, QuerydslPredicateExecutor<Subscription>{
    Optional<Subscription> findSubscriptionByCompanyIdAndActiveTrue(Long companyId);

}
