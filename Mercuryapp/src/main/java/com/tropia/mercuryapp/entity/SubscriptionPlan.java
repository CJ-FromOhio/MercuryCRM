package com.tropia.mercuryapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "subscription_plans")
@Entity
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price", nullable = false, scale = 2)
    private BigDecimal price;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "max_workers", nullable = false)
    private Integer maxWorkers;
    @Column(name = "active", nullable = false)
    private Boolean active;
}
