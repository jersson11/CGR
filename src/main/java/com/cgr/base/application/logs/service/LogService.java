package com.cgr.base.application.logs.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cgr.base.application.logs.dto.LogDto;
import com.cgr.base.application.logs.usecase.LogUseCase;
import com.cgr.base.domain.repository.ILogRepository;
import com.cgr.base.infrastructure.persistence.entity.LogEntity;
import com.cgr.base.infrastructure.utilities.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService implements LogUseCase {

    private final ILogRepository adapterLogRepository;

    private final DtoMapper dtoMapper;

    @Override
    public List<LogDto> logFindAll() {
        return this.dtoMapper.convertToListDto(this.adapterLogRepository.logFindAll(), LogDto.class);
    }

    @Override
    public LogEntity createLog(String userName) {
        LogEntity logEntity = new LogEntity("n/a", new Date(), true, userName);
        return this.adapterLogRepository.createLog(logEntity, userName);
    }

}
