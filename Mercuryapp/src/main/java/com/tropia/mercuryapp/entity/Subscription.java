package com.tropia.mercuryapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"company", "plan", "transactions"})
@EqualsAndHashCode(exclude = {"company", "plan", "transactions"})
@Table(name = "subscriptions")
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private SubscriptionPlan plan;
    @OneToMany(
            mappedBy = "subscription",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<PaymentTransaction> transactions =  new ArrayList<>();
    @Column(name = "start_date")
    private Instant startDate;
    @Column(name = "end_date")
    private Instant endDate;
    @Column(name = "active")
    private Boolean active;

    public void addTransaction(PaymentTransaction transaction) {
        transactions.add(transaction);
        transaction.setSubscription(this);
    }
}
