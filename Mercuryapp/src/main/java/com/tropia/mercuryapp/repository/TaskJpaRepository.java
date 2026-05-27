package com.tropia.mercuryapp.repository;

import com.tropia.mercuryapp.entity.Client;
import com.tropia.mercuryapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskJpaRepository extends JpaRepository<Task, Long>, QuerydslPredicateExecutor<Task>{
    List<Task> findByCompanyId(Long companyId);
    List<Task> findByWorkerId(Long workerId);
    List<Task> findByClientId(Long clientId);

}
