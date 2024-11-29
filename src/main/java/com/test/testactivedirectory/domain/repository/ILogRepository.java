package com.test.testactivedirectory.domain.repository;

import java.util.List;

import com.test.testactivedirectory.infrastructure.persistence.entity.LogEntity;

public interface ILogRepository {

    public abstract List<LogEntity> logFindAll();

    public abstract LogEntity createLog(LogEntity logEntity, Long idUser);

}
