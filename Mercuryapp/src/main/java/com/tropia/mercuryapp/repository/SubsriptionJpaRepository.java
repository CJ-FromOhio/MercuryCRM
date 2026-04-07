package com.tropia.mercuryapp.repository;

import com.tropia.mercuryapp.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsriptionJpaRepository extends JpaRepository<Subscription, Long> {
}
