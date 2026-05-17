package com.tropia.mercuryapp.repository;

import com.tropia.mercuryapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPhoneNumber(String phoneNumber);
    Optional<Client> findByEmail(String email);
    List<Client> findByCompanyId(Long companyId);
}
