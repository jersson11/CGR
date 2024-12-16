package com.cgr.base.infrastructure.persistence.repository.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgr.base.infrastructure.persistence.entity.LogEntity;

@Repository
public interface LogsRepositoryJpa extends JpaRepository<LogEntity, Long> {
    
}
