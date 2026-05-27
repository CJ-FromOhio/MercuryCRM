package com.tropia.mercuryapp.repository;

import com.tropia.mercuryapp.entity.Client;
import com.tropia.mercuryapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>{
    Integer countByCompanyId(Long companyId);
}
