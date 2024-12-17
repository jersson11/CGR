package com.cgr.base.application.logs.usecase;

import java.util.List;

import com.cgr.base.application.auth.dto.AuthRequestDto;
import com.cgr.base.application.logs.dto.LogDto;
import com.cgr.base.infrastructure.persistence.entity.LogEntity;

public interface ILogUseCase {
    public abstract List<LogDto> logFindAll();

    public abstract LogEntity createLog(AuthRequestDto userRequest);
}
