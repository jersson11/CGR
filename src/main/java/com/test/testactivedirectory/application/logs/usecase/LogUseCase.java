package com.test.testactivedirectory.application.logs.usecase;

import java.util.List;

import com.test.testactivedirectory.application.logs.dto.LogDto;
import com.test.testactivedirectory.infrastructure.persistence.entity.LogEntity;

public interface LogUseCase {
    public abstract List<LogDto> logFindAll();

    public abstract LogEntity createLog(String userName);
}
