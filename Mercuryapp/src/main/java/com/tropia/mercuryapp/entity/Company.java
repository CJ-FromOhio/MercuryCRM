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
@ToString(exclude = {"workers"})
@EqualsAndHashCode(exclude = {"workers","address"})
@Table(name = "company")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "address")
    private String address;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private User director;
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<PaymentTransaction> transactions =  new ArrayList<>();
    @Column(name = "created_at")
    private Instant createdAt;
    @OneToMany(
            mappedBy = "company",
            fetch = FetchType.LAZY
    )
    private List<User> workers;

    public void setDirector(User director) {
        this.director = director;
        if(director != null){
            director.setCompany(this);
        }
    }
}
