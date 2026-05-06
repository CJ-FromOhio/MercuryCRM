package com.tropia.mercuryapp.repository;

import com.tropia.mercuryapp.entity.Company;
import com.tropia.mercuryapp.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends JpaRepository<Company, Long> {
}
