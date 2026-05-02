package com.tropia.mercuryapp.service;

import com.tropia.mercuryapp.entity.Company;
import com.tropia.mercuryapp.entity.PaymentStatus;
import com.tropia.mercuryapp.entity.PaymentTransaction;
import com.tropia.mercuryapp.repository.PaymentTransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PaymentTransactionService {

    private final PaymentTransactionJpaRepository paymentTransactionJpaRepository;
    @Transactional
    public PaymentTransaction create(BigDecimal amount, String currency, Company company) {

        boolean isSuccess = ThreadLocalRandom.current().nextDouble()>0.1;

        PaymentTransaction pt = PaymentTransaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .price(amount)
                .currency(currency)
                .status(isSuccess ? PaymentStatus.COMPLETED : PaymentStatus.FAILED)
                .createdAt(Instant.now())
                .company(company)
                .build();
        paymentTransactionJpaRepository.save(pt);
        return pt;
    }

}
