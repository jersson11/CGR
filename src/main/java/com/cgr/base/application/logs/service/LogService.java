package com.cgr.base.application.logs.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cgr.base.application.auth.dto.AuthRequestDto;
import com.cgr.base.application.logs.dto.LogDto;
import com.cgr.base.application.logs.usecase.ILogUseCase;
import com.cgr.base.domain.repository.ILogRepository;
import com.cgr.base.infrastructure.persistence.entity.LogEntity;
import com.cgr.base.infrastructure.utilities.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService implements ILogUseCase {

    private final ILogRepository adapterLogRepository;

    private final DtoMapper dtoMapper;

    @Override
    public List<LogDto> logFindAll() {
        return this.dtoMapper.convertToListDto(this.adapterLogRepository.logFindAll(), LogDto.class);
    }

    @Override
    public LogEntity createLog(AuthRequestDto userRequest) {
        LogEntity logEntity = new LogEntity(userRequest.getEmail(), new Date(), true, userRequest.getSAMAccountName());
        return this.adapterLogRepository.createLog(logEntity, userRequest.getSAMAccountName());
    }

}
