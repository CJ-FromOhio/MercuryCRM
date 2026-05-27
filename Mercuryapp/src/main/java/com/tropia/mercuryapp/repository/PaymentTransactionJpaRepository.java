package com.tropia.mercuryapp.repository;

import com.tropia.mercuryapp.entity.Client;
import com.tropia.mercuryapp.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransactionJpaRepository extends JpaRepository<PaymentTransaction, Long>, QuerydslPredicateExecutor<PaymentTransaction>{
}
