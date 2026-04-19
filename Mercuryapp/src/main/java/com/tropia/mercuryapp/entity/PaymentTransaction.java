package com.tropia.mercuryapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"subscription"})
@EqualsAndHashCode(exclude = {"subscription","status"})
@Table(name = "payment_transaction")
@Entity
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
    @Column(name = "price", nullable = false, scale = 2)
    private BigDecimal price;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "created_at")
    private Instant createdAt;
}
