package com.test.testactivedirectory.infrastructure.persistence.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.domain.repository.ILogRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.LogEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.logs.LogsRepositoryJpa;

@Component
public class LogRepositoryAdapterImpl implements ILogRepository {

    private final LogsRepositoryJpa logRepositoryJpa;

    public LogRepositoryAdapterImpl(LogsRepositoryJpa logRepositoryJpa) {
        this.logRepositoryJpa = logRepositoryJpa;
    }

    @Transactional
    @Override
    public List<LogEntity> logFindAll() {
        return this.logRepositoryJpa.findAll();
    }

    @Override
    public LogEntity createLog(LogEntity logEntity) {
        LogEntity log = this.logRepositoryJpa.save(logEntity);
        return log;
    }

}
