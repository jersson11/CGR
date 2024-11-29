package com.test.testactivedirectory.infrastructure.persistence.repository.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.testactivedirectory.infrastructure.persistence.entity.LogEntity;

@Repository
public interface LogsRepositoryJpa extends JpaRepository<LogEntity, Long> {
    
}
